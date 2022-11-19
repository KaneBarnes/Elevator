// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.frcteam3255.utils.SN_InstantCommand;
import com.frcteam3255.utils.SN_Math;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap.mapElevator;
import frc.robot.RobotPreferences.prefElevator;
import frc.robot.Constants.constElevator;;

public class Elevator extends SubsystemBase {
  TalonFX elevatorMotor;
  TalonFXConfiguration config;

  DigitalInput minSwitch;
  DigitalInput maxSwitch;

  boolean displayOnDashboard;

  /** Creates a new Elevator. */
  public Elevator() {
    elevatorMotor = new TalonFX(mapElevator.ELEVATOR_MOTOR_CAN);

    minSwitch = new DigitalInput(mapElevator.ELEVATOR_MINIMUM_SWITCH_DIO);
    maxSwitch = new DigitalInput(mapElevator.ELEVATOR_MAXIMUM_SWITCH_DIO);

    config = new TalonFXConfiguration();
    configure();

    displayOnDashboard = true;
  }

  private final Elevator subElevator = new Elevator();

  public void configure() {
    config.slot0.kP = prefElevator.elevatorP.getValue();
    config.slot0.kI = prefElevator.elevatorI.getValue();
    config.slot0.kD = prefElevator.elevatorD.getValue();

    config.slot0.closedLoopPeakOutput = prefElevator.elevatorClosedLoopSpeed.getValue();
    config.slot0.allowableClosedloopError = SN_Math.RPMToVelocity(
        prefElevator.elevatorAllowableClosedLoopError.getValue(), SN_Math.TALONFX_ENCODER_PULSES_PER_COUNT);

    elevatorMotor.configFactoryDefault();
    elevatorMotor.configAllSettings(config);

    elevatorMotor.configReverseSoftLimitEnable(false);
    elevatorMotor.configForwardSoftLimitEnable(true);
    elevatorMotor.configForwardSoftLimitThreshold(prefElevator.elevatorAngledMaxPos.getValue());

    elevatorMotor.setNeutralMode(NeutralMode.Brake);
    elevatorMotor.setInverted(constElevator.INVERTED);
  }

  public void setElevatorSpeed(double a_speed) {

    double speed = a_speed;

    // cannot ever go below min switch
    if ((isMinSwitch() && speed < 0)) {
      speed = 0;
    }

    // cannot ever go below min switch
    if ((isMaxSwitch() && speed > 0)) {
      speed = 0;
    }

  }

  public double getElevatorEncoderCounts() {
    return elevatorMotor.getSelectedSensorPosition();
  }

  public void resetElevatorEncoderCounts() {
    elevatorMotor.setSelectedSensorPosition(0);
  }

  public boolean isMaxSwitch() {
    return !maxSwitch.get();
  }

  public boolean isMinSwitch() {
    return !minSwitch.get();
  }

  public void displayValuesOnDashboard() {
    displayOnDashboard = true;
  }

  public void hideValuesOnDashboard() {
    displayOnDashboard = false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // This method will be called once per scheduler run
    if (getElevatorEncoderCounts() > prefElevator.elevatorSlowdownMinThresholdEncoderCounts.getValue()
        && getElevatorEncoderCounts() < prefElevator.elevatorSlowdownMaxThresholdEncoderCounts.getValue()) {
      elevatorMotor.configClosedLoopPeakOutput(0, prefElevator.elevatorSlowdownSpeed.getValue());
    } else {
      elevatorMotor.configClosedLoopPeakOutput(0, prefElevator.elevatorClosedLoopSpeed.getValue());
    }
    if (displayOnDashboard) {
      SmartDashboard.putNumber("Elevator Encoder Counts", getElevatorEncoderCounts());
      SmartDashboard.putBoolean("Elevator Is At Minimum Switch", isMinSwitch());
      SmartDashboard.putBoolean("Elevator Is At Maximum Switch", isMaxSwitch());
    }
    if (isMinSwitch()) {
      resetElevatorEncoderCounts();
    }

    SmartDashboard.putData(
        "Configue Elevator", new SN_InstantCommand(subElevator::configure, true, subElevator));
  }
}
