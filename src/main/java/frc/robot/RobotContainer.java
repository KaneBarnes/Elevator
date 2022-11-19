// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.frcteam3255.joystick.SN_F310Gamepad;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.RobotMap.mapControllers;
import frc.robot.commands.PositionElevator;
import frc.robot.subsystems.Elevator;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final PositionElevator m_autoCommand = new PositionElevator();
  private final SN_F310Gamepad conDriver = new SN_F310Gamepad(mapControllers.DRIVER_CONTROLLER);
  private final Elevator subElevator = new Elevator();

  public RobotContainer() {
    // Configure the button bindings
    subElevator.setDefaultCommand(new RunCommand(() -> subElevator.setElevatorSpeed(conDriver.getAxisLSY())));

    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing
   * it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    // Climber

    // Button assigned controls
    // Number equal to encoder counts

    conDriver.btn_A
        .whenPressed(() -> subElevator.setElevatorPosition(0));

    conDriver.btn_Y
        .whenPressed(() -> subElevator.setElevatorPosition(69420));

    // Manual control

    conDriver.btn_RStick
        .whenPressed(() -> subElevator.setElevatorSpeed(0.1))
        .whenReleased(() -> subElevator.setElevatorSpeed(0));

    conDriver.btn_LStick
        .whenPressed(() -> subElevator.setElevatorSpeed(-0.1))
        .whenReleased(() -> subElevator.setElevatorSpeed(0));

    // Controllers

    conDriver.btn_Start
        .whenPressed(() -> subElevator.resetElevatorEncoderCounts());

    conDriver.btn_LBump
        .whenPressed(() -> subElevator.hideValuesOnDashboard());

    conDriver.btn_RBump
        .whenPressed(() -> subElevator.displayValuesOnDashboard());

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An PositionElevator will run in autonomous
    return m_autoCommand;
  }
}
