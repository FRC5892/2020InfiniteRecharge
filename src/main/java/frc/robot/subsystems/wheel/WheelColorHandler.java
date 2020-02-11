package frc.robot.subsystems.wheel;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.util.Color;

public class WheelColorHandler {
    private final ColorSensorV3 sensor;
    private final ColorMatch matcher;
    private final Color blueTarget, greenTarget, redTarget, yellowTarget;

    public WheelColorHandler() {
        sensor = new ColorSensorV3(Port.kOnboard);

        ColorDescriptions colors;
        try {
            colors = new ObjectMapper().readValue(new File("/home/lvuser/deploy/wheel-colors.json"),
                    ColorDescriptions.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        blueTarget = new Color(colors.blue[0], colors.blue[1], colors.blue[2]);
        greenTarget = new Color(colors.green[0], colors.green[1], colors.green[2]);
        redTarget = new Color(colors.red[0], colors.red[1], colors.red[2]);
        yellowTarget = new Color(colors.yellow[0], colors.yellow[1], colors.yellow[2]);

        matcher = new ColorMatch();
        matcher.addColorMatch(blueTarget);
        matcher.addColorMatch(greenTarget);
        matcher.addColorMatch(redTarget);
        matcher.addColorMatch(yellowTarget);
    }

    public Color detectedColor() {
        return sensor.getColor();
    }

    public WheelColor colorWeSee() {
        var color = sensor.getColor();
        var match = matcher.matchClosestColor(color);
        if (match.color == blueTarget) {
            return WheelColor.BLUE;
        } else if (match.color == greenTarget) {
            return WheelColor.GREEN;
        } else if (match.color == redTarget) {
            return WheelColor.RED;
        } else if (match.color == yellowTarget) {
            return WheelColor.YELLOW;
        } else {
            return WheelColor.UNKNOWN;
        }
    }

    public WheelColor colorTheySee() {
        var us = colorWeSee();
        if (us == WheelColor.UNKNOWN) {
            return WheelColor.UNKNOWN;
        } else {
            return WheelColor.values()[(us.ordinal() + 2) % 4];
        }
    }

    public WheelColor colorTheyWant() {
        return WheelColor.forChar(DriverStation.getInstance().getGameSpecificMessage().charAt(0));
    }

    private static final int[] dttRets = { 0, -1, 1, 1 };

    /**
     * where 1 is clockwise
     */
    public int directionToTurn() {
        var want = colorTheyWant();
        if (want == WheelColor.UNKNOWN) {
            return 0;
        }
        var see = colorTheySee();
        if (see == WheelColor.UNKNOWN) {
            return 1;
        }

        var index = (want.ordinal() - see.ordinal() + 4) % 4;
        return dttRets[index];
    }

    public static class ColorDescriptions {
        public double[] blue, green, red, yellow;
    }

    public static enum WheelColor {
        BLUE('B'), GREEN('G'), RED('R'), YELLOW('Y'), UNKNOWN('?');

        public final char character;

        WheelColor(char character) {
            this.character = character;
        }

        public static WheelColor forChar(char character) {
            for (var value : WheelColor.values()) {
                if (value.character == character) {
                    return value;
                }
            }
            return WheelColor.UNKNOWN;
        }
    }
}