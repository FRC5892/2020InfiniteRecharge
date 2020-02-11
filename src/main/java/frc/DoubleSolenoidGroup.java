package frc;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class DoubleSolenoidGroup {
    private DoubleSolenoid[] solenoids;

    public DoubleSolenoidGroup(DoubleSolenoid... solenoids) {
        this.solenoids = solenoids;
    }

    public DoubleSolenoidGroup(int[][] ports) {
        this(createSolenoids(ports));
    }

    private static DoubleSolenoid[] createSolenoids(int[][] ports) {
        var ret = new DoubleSolenoid[ports.length];
        for (var i = 0; i < ports.length; i++) {
            ret[i] = new DoubleSolenoid(ports[i][0], ports[i][1]);
        }
        return ret;
    }

    public void set(Value value) {
        for (var sol : solenoids) {
            sol.set(value);
        }
    }
}