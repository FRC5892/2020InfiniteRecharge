package frc;

import java.util.ArrayList;
import java.util.Arrays;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class SolenoidGroup {
    private final Solenoid[] singleSolenoids;
    private final DoubleSolenoid[] doubleSolenoids;

    public SolenoidGroup(Solenoid[] singleSolenoids, DoubleSolenoid[] doubleSolenoids) {
        this.singleSolenoids = singleSolenoids;
        this.doubleSolenoids = doubleSolenoids;
    }

    public static SolenoidGroup forPorts(int[]... ports) {
        return new Factory().addPorts(ports).construct();
    }

    public void set(boolean on) {
        for (var sol : singleSolenoids) {
            sol.set(on);
        }
        for (var sol : doubleSolenoids) {
            sol.set(on ? Value.kForward : Value.kReverse);
        }
    }

    public void set(Value value) {
        switch (value) {
        case kForward:
            for (var sol : singleSolenoids) {
                sol.set(true);
            }
            break;
        case kReverse:
            for (var sol : singleSolenoids) {
                sol.set(false);
            }
            break;
        case kOff:
            break;
        }
        for (var sol : doubleSolenoids) {
            sol.set(value);
        }
    }

    public void close() {
        for (var sol : singleSolenoids) {
            sol.close();
        }
        for (var sol : doubleSolenoids) {
            sol.close();
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