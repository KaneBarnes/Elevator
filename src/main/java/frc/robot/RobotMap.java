// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

public final class RobotMap {

  public static final class mapElevator {

    public static final int ELEVATOR_MOTOR_CAN = 10;
    public static final int ELEVATOR_MINIMUM_SWITCH_DIO = 6;
    public static final int ELEVATOR_MAXIMUM_SWITCH_DIO = 0;

    public static final int PIVOT_PISTON_SOLENOID_PCM_A = 2;
    public static final int PIVOT_PISTON_SOLENOID_PCM_B = 3;
  }

  public static final class mapControllers {

    public static final int DRIVER_CONTROLLER = 0;
    public static final int SWITCHBOARD = 2;
  }
}