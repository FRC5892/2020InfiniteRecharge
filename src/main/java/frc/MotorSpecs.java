package frc;

import java.util.Arrays;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class MotorSpecs {
    public String type;
    public int port;
    public boolean inverted;

    private static WPI_VictorSPX victorSpxSetup(WPI_VictorSPX motor) {
        motor.setNeutralMode(NeutralMode.Brake);
        return motor;
    }

    private static WPI_TalonSRX talonSrxSetup(WPI_TalonSRX motor) {
        motor.setNeutralMode(NeutralMode.Brake);
        return motor;
    }

    private static CANSparkMax sparkMaxSetup(CANSparkMax motor) {
        motor.setIdleMode(IdleMode.kBrake);
        motor.setSmartCurrentLimit(40);
        motor.setOpenLoopRampRate(0.5);
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
            break;
        case "VictorSPX":
            ret = victorSpxSetup(new WPI_VictorSPX(port));
            break;
        case "TalonSRX":
            ret = talonSrxSetup(new WPI_TalonSRX(port));
            break;
        case "SparkMax":
            ret = sparkMaxSetup(new CANSparkMax(port, MotorType.kBrushless));
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
            return setInverted(talonSrxSetup(new WPI_TalonSRX(port)), inverted);
        } else {
            throw new MotorError("not a Talon SRX");
        }
    }

    public void makeTalonFollower(WPI_TalonSRX main) {
        switch (type) {
        case "VictorSPX":
            setInverted(victorSpxSetup(new WPI_VictorSPX(port)), inverted).follow(main);
            break;
        case "TalonSRX":
            setInverted(talonSrxSetup(new WPI_TalonSRX(port)), inverted).follow(main);
            break;
        default:
            throw new MotorError("not a Victor SPX or Talon SRX");
        }
    }

    public CANSparkMax makeSparkMax() {
        if (type.equals("SparkMax")) {
            return setInverted(sparkMaxSetup(new CANSparkMax(port, MotorType.kBrushless)), inverted);
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

    public static CANSparkMax makeSparkMaxes(MotorSpecs[] specs) {
        var main = specs[0].makeSparkMax();
        for (var i = 1; i < specs.length; i++) {
            specs[i].makeSparkMax().follow(main, specs[i].inverted);
        }
        return main;
    }

    public static class MotorError extends Error {
        private static final long serialVersionUID = 1L;

        private MotorError(String message) {
            super(message);
        }
    }
}