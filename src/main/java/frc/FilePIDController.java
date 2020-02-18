package frc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.controller.PIDController;

public class FilePIDController extends PIDController {
    public FilePIDController(String filename) {
        super(0, 0, 0);
        readGainsFromFile(filename);
    }

    public void readGainsFromFile(String filename) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            try {
                var kP = Double.parseDouble(reader.readLine());
                var kI = Double.parseDouble(reader.readLine());
                var kD = Double.parseDouble(reader.readLine());
                setPID(kP, kI, kD);
            } finally {
                reader.close();
            }
        } catch (IOException | NumberFormatException e) {
            DriverStation.reportWarning(e.toString(), e.getStackTrace());
        }
    }
}