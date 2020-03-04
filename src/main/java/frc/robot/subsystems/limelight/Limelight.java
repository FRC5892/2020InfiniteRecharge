package frc.robot.subsystems.limelight;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Limelight extends SubsystemBase {
    private final NetworkTable table;
    private final NetworkTableEntry tv, tx, ty, ta, ledMode, camMode, pipeline;

    public Limelight() {
        this("limelight");
        setDefaultCommand(new MaintainLimelightDefaults(this));       
    }

    public Limelight(String tableName) {
        table = NetworkTableInstance.getDefault().getTable(tableName);
        tv = table.getEntry("tv");
        tx = table.getEntry("tx");
        ty = table.getEntry("ty");
        ta = table.getEntry("ta");
        ledMode = table.getEntry("ledMode");
        camMode = table.getEntry("camMode");
        pipeline = table.getEntry("pipeline");
    }

    public boolean targetValid() {
        return tv.getDouble(0) > 1;
    }

    public double xOffset() {
        return tx.getDouble(0);
    }

    public double yOffset() {
        return ty.getDouble(0);
    }

    public double targetArea() {
        return ta.getDouble(0);
    }

    public enum LEDMode {
        DEFAULT(0), OFF(1), BLINK(2), ON(3);

        private double value;

        LEDMode(double value) {
            this.value = value;
        }
    }

    public void setLedMode(LEDMode mode) {
        ledMode.setDouble(mode.value);
    }

    public void setDriverCamera(boolean driverCam) {
        camMode.setDouble(driverCam ? 1 : 0);
    }

    public void setPipeline(int pipeline) {
        this.pipeline.setDouble(pipeline);
    }

    // FIXME: mounting measurements are guesses, take actual measurements
    private static final double MOUNTING_ANGLE = 30; // degrees
    private static final double MOUNTING_HEIGHT = 18; // inches
    private static final double TARGET_HEIGHT = 98.25; // inches

    public double estimateTargetDistance() {
        return (TARGET_HEIGHT - MOUNTING_HEIGHT) / Math.tan(Math.toRadians(yOffset() + MOUNTING_ANGLE));
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Limelight Distance", estimateTargetDistance());
    }
}