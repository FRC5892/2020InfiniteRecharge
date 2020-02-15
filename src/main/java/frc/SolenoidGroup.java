package frc;

import java.util.ArrayList;
import java.util.Arrays;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;

public class SolenoidGroup implements Sendable {
    private final Solenoid[] singleSolenoids;
    private final DoubleSolenoid[] doubleSolenoids;

    private boolean on = false;
    private Value value = Value.kOff;

    private static int instances;

    public SolenoidGroup(Solenoid[] singleSolenoids, DoubleSolenoid[] doubleSolenoids) {
        this.singleSolenoids = singleSolenoids;
        for (var sol : singleSolenoids) {
            SendableRegistry.addChild(this, sol);
        }
        this.doubleSolenoids = doubleSolenoids;
        for (var sol : doubleSolenoids) {
            SendableRegistry.addChild(this, sol);
        }
        SendableRegistry.addLW(this, "tSolenoidGroup", ++instances);
    }

    public static SolenoidGroup forPorts(int[]... ports) {
        return new Factory().addPorts(ports).construct();
    }

    protected void setSingles(boolean on) {
        for (var sol : singleSolenoids) {
            sol.set(on);
        }
        this.on = on;
    }

    protected void setDoubles(Value value) {
        for (var sol : doubleSolenoids) {
            sol.set(value);
        }
        this.value = value;
    }

    public boolean getOn() {
        return on;
    }

    public Value getValue() {
        return value;
    }

    public void reset() {
        setSingles(false);
        setDoubles(Value.kOff);
    }

    public void set(boolean on) {
        setSingles(on);
        setDoubles(on ? Value.kForward : Value.kReverse);
    }

    public void set(Value value) {
        switch (value) {
        case kForward:
            setSingles(true);
            break;
        case kReverse:
            setSingles(false);
            break;
        case kOff:
            break;
        }
        setDoubles(value);
    }

    public void close() {
        for (var sol : singleSolenoids) {
            sol.close();
        }
        for (var sol : doubleSolenoids) {
            sol.close();
        }
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        if (doubleSolenoids.length != 0) {
            // based on DoubleSolenoid.initSendable
            builder.setSmartDashboardType("Double Solenoid");
            builder.setActuator(true);
            builder.setSafeState(this::reset);
            builder.addStringProperty("Value", () -> getValue().name().substring(1), value -> {
                if ("Forward".equals(value)) {
                    set(Value.kForward);
                } else if ("Reverse".equals(value)) {
                    set(Value.kReverse);
                } else {
                    set(Value.kOff);
                }
            });
        } else {
            // based on Solenoid.initSendable
            builder.setSmartDashboardType("Solenoid");
            builder.setActuator(true);
            builder.setSafeState(this::reset);
            builder.addBooleanProperty("Value", this::getOn, this::set);
        }
    }

    public static class Factory {
        private final ArrayList<Solenoid> singleSolenoids = new ArrayList<>();
        private final ArrayList<DoubleSolenoid> doubleSolenoids = new ArrayList<>();

        Factory add(Solenoid... solenoids) {
            for (var s : solenoids) {
                singleSolenoids.add(s);
            }
            return this;
        }

        Factory add(DoubleSolenoid... solenoids) {
            for (var s : solenoids) {
                doubleSolenoids.add(s);
            }
            return this;
        }

        Factory addPorts(int[]... ports) {
            for (var p : ports) {
                if (p.length == 1) {
                    singleSolenoids.add(new Solenoid(p[0]));
                } else if (p.length == 2) {
                    doubleSolenoids.add(new DoubleSolenoid(p[0], p[1]));
                } else {
                    throw new SolenoidGroupFactoryException(p);
                }
            }
            return this;
        }

        SolenoidGroup construct() {
            return new SolenoidGroup(singleSolenoids.toArray(new Solenoid[0]),
                    doubleSolenoids.toArray(new DoubleSolenoid[0]));
        }
    }

    private static class SolenoidGroupFactoryException extends RuntimeException {
        private static final long serialVersionUID = 1L;

        final int[] offender;

        SolenoidGroupFactoryException(int[] offender) {
            this.offender = offender;
        }

        @Override
        public String toString() {
            var ret = new StringBuffer("solenoids can only have one or two ports: ");
            ret.append(Arrays.toString(offender));
            return ret.toString();
        }

    }
}