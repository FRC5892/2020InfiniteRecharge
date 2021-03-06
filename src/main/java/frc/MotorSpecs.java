package frc;

import java.util.ArrayList;
import java.util.Arrays;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class MotorSpecs {
    public String type;
    public int port;
    public boolean inverted;
    public String mode;

    private static ArrayList<WPI_VictorSPX> drivelikeVictorSPXs = new ArrayList<>();
    private static ArrayList<WPI_TalonSRX> drivelikeTalonSRXs = new ArrayList<>();
    private static ArrayList<CANSparkMax> drivelikeSparkMaxes = new ArrayList<>();

    private static WPI_VictorSPX victorSpxSetup(WPI_VictorSPX motor, String mode) {
        switch (mode) {
        case "Brake":
            motor.setNeutralMode(NeutralMode.Brake);
            break;
        case "Coast":
            motor.setNeutralMode(NeutralMode.Coast);
            break;
        case "Drivelike":
            drivelikeVictorSPXs.add(motor);
            break;
        }
        return motor;
    }

    private static WPI_TalonSRX talonSrxSetup(WPI_TalonSRX motor, String mode) {
        switch (mode) {
        case "Brake":
            motor.setNeutralMode(NeutralMode.Brake);
            break;
        case "Coast":
            motor.setNeutralMode(NeutralMode.Coast);
            break;
        case "Drivelike":
            drivelikeTalonSRXs.add(motor);
            break;
        }
        return motor;
    }

    private static CANSparkMax sparkMaxSetup(CANSparkMax motor, String mode) {
        switch (mode) {
        case "Brake":
            motor.setIdleMode(IdleMode.kBrake);
            break;
        case "Coast":
            motor.setIdleMode(IdleMode.kCoast);
            break;
        case "Drivelike":
            drivelikeSparkMaxes.add(motor);
            break;
        }
        motor.setSmartCurrentLimit(40);
        return motor;
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        if (inverted) {
            sb.append("inverted ");
        }
        sb.append(type);
        sb.append(" on port " + port);
        return sb.toString();
    }

    public static <T extends SpeedController> T setInverted(T motor, boolean inverted) {
        motor.setInverted(inverted);
        return motor;
    }

    public SpeedController makeSpeedController() {
        SpeedController ret;
        switch (type) {
        case "VictorSP":
            ret = new VictorSP(port);
            if (mode != null) {
                DriverStation.reportWarning("Unusable mode specified for PWM controller " + port, false);
            }
            break;
        case "VictorSPX":
            ret = victorSpxSetup(new WPI_VictorSPX(port), mode);
            break;
        case "TalonSRX":
            ret = talonSrxSetup(new WPI_TalonSRX(port), mode);
            break;
        case "SparkMax":
            ret = sparkMaxSetup(new CANSparkMax(port, MotorType.kBrushless), mode);
            break;
        case "Dummy":
            ret = new DummySpeedController();
            break;
        default:
            throw new MotorError("unrecognized motor type " + type);
        }
        ret.setInverted(inverted);
        return ret;
    }

    public WPI_TalonSRX makeTalonSRX() {
        if (type.equals("TalonSRX")) {
            return setInverted(talonSrxSetup(new WPI_TalonSRX(port), mode), inverted);
        } else {
            throw new MotorError("not a Talon SRX");
        }
    }

    public void makeTalonFollower(WPI_TalonSRX main) {
        switch (type) {
        case "VictorSPX":
            setInverted(victorSpxSetup(new WPI_VictorSPX(port), mode), inverted).follow(main);
            break;
        case "TalonSRX":
            setInverted(talonSrxSetup(new WPI_TalonSRX(port), mode), inverted).follow(main);
            break;
        default:
            throw new MotorError("not a Victor SPX or Talon SRX");
        }
    }

    @SuppressWarnings("resource")
    public CANSparkMax makeSparkMax() {
        if (type.equals("SparkMax")) {
            return setInverted(sparkMaxSetup(new CANSparkMax(port, MotorType.kBrushless), mode), inverted);
        } else {
            throw new MotorError("not a SPARK MAX");
        }
    }

    private static SpeedControllerGroup makeGroup(SpeedController[] controllers) {
        if (controllers.length == 0) {
            return new SpeedControllerGroup(new DummySpeedController());
        }
        return new SpeedControllerGroup(controllers[0], Arrays.copyOfRange(controllers, 1, controllers.length));
    }

    private static SpeedControllerGroup _makeSpeedControllers(MotorSpecs[] specs) {
        var controllers = new SpeedController[specs.length];
        for (var i = 0; i < specs.length; i++) {
            controllers[i] = specs[i].makeSpeedController();
        }
        return makeGroup(controllers);
    }

    public static SpeedController makeSpeedControllers(MotorSpecs[] specs) {
        return _makeSpeedControllers(specs);
    }

    public static SpeedController makeSpeedControllers(MotorSpecs[] specs, String name, SubsystemBase subsystem) {
        var ret = _makeSpeedControllers(specs);
        subsystem.addChild(name, ret);
        return ret;
    }

    public static WPI_TalonSRX makeTalonAndFollowers(MotorSpecs[] specs) {
        var main = specs[0].makeTalonSRX();
        for (var i = 1; i < specs.length; i++) {
            specs[i].makeTalonFollower(main);
        }
        return main;
    }

    public static WPI_TalonSRX makeTalonAndFollowers(MotorSpecs[] specs, String name, SubsystemBase subsystem) {
        var ret = makeTalonAndFollowers(specs);
        subsystem.addChild(name, ret);
        return ret;
    }

    private static Tuples.Two<SpeedControllerGroup, CANEncoder> _makeSparkMaxGroup(MotorSpecs[] specs) {
        var main = specs[0].makeSparkMax();
        var rest = new CANSparkMax[specs.length - 1];
        for (var i = 1; i < specs.length; i++) {
            rest[i - 1] = specs[i].makeSparkMax();
        }
        return new Tuples.Two<>(new SpeedControllerGroup(main, rest), main.getEncoder());
    }

    public static Tuples.Two<SpeedController, CANEncoder> makeSparkMaxGroup(MotorSpecs[] specs) {
        var ret = _makeSparkMaxGroup(specs);
        return new Tuples.Two<>(ret.first, ret.second);
    }

    public static Tuples.Two<SpeedController, CANEncoder> makeSparkMaxGroup(MotorSpecs[] specs, String name,
            SubsystemBase subsystem) {
        var ret = _makeSparkMaxGroup(specs);
        subsystem.addChild(name, ret.first);
        return new Tuples.Two<>(ret.first, ret.second);
    }

    public static CANSparkMax makeSparkMaxAndFollowers(MotorSpecs[] specs) {
        var main = specs[0].makeSparkMax();
        for (var i = 1; i < specs.length; i++) {
            specs[i].makeSparkMax().follow(main, specs[i].inverted ^ specs[0].inverted);
        }
        return main;
    }

    public static void coastDrivelikes() {
        for (var m : drivelikeVictorSPXs) {
            m.setNeutralMode(NeutralMode.Coast);
        }
        for (var m : drivelikeTalonSRXs) {
            m.setNeutralMode(NeutralMode.Coast);
        }
        for (var m : drivelikeSparkMaxes) {
            m.setIdleMode(IdleMode.kCoast);
        }
    }

    public static void brakeDrivelikes() {
        for (var m : drivelikeVictorSPXs) {
            m.setNeutralMode(NeutralMode.Brake);
        }
        for (var m : drivelikeTalonSRXs) {
            m.setNeutralMode(NeutralMode.Brake);
        }
        for (var m : drivelikeSparkMaxes) {
            m.setIdleMode(IdleMode.kBrake);
        }
    }

    public static class MotorError extends Error {
        private static final long serialVersionUID = 1L;

        private MotorError(String message) {
            super(message);
        }
    }
}