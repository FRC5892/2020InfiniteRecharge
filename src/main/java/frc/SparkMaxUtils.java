package frc;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.networktables.EntryListenerFlags;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SparkMaxUtils {
    private SparkMaxUtils() {
    }

    public static void readPID(CANSparkMax spark, String name, boolean tuningMode) throws IOException {
        double kP, kI, kD, kF;
        try (BufferedReader reader = new BufferedReader(new FileReader("/home/lvuser/deploy/PID/" + name + ".txt"))) {
            kP = Double.parseDouble(reader.readLine());
            kI = Double.parseDouble(reader.readLine());
            kD = Double.parseDouble(reader.readLine());
            kF = Double.parseDouble(reader.readLine());
        }
        var controller = spark.getPIDController();
        controller.setP(kP);
        controller.setI(kI);
        controller.setD(kD);
        controller.setFF(kF);
        if (tuningMode) {
            makeShuffleboardTab(spark, name, kP, kI, kD, kF);
        }
        spark.setIdleMode(IdleMode.kBrake);
    }

    @SuppressWarnings("resource")
    private static void makeShuffleboardTab(CANSparkMax spark, String name, double kP, double kI, double kD, double kF) {
        var tab = Shuffleboard.getTab(name);
        var controller = spark.getPIDController();

        var kPEntry = tab.add("kP", kP).withPosition(0, 0).getEntry();
        kPEntry.setDouble(kP);
        kPEntry.addListener((evt) -> {
            controller.setP(evt.value.getDouble());
        }, EntryListenerFlags.kUpdate);

        var kIEntry = tab.add("kI", kI).withPosition(0, 1).getEntry();
        kIEntry.setDouble(kI);
        kIEntry.addListener((evt) -> {
            controller.setI(evt.value.getDouble());
        }, EntryListenerFlags.kUpdate);

        var kDEntry = tab.add("kD", kD).withPosition(0, 2).getEntry();
        kDEntry.setDouble(kD);
        kDEntry.addListener((evt) -> {
            controller.setD(evt.value.getDouble());
        }, EntryListenerFlags.kUpdate);

        var kFEntry = tab.add("kF", kF).withPosition(0, 3).getEntry();
        kFEntry.setDouble(kF);
        kFEntry.addListener((evt) -> {
            controller.setFF(evt.value.getDouble());
        }, EntryListenerFlags.kUpdate);

        var inputEntry = tab.add("Input", 0).withPosition(1, 0).getEntry();
        var outputEntry = tab.add("Output", 0).withPosition(1, 1).getEntry();
        new Notifier(() -> {
            inputEntry.setDouble(spark.getEncoder().getVelocity());
            outputEntry.setDouble(spark.getAppliedOutput());
        }).startPeriodic(1.0 / 20);
    }
}