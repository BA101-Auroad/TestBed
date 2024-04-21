// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.commands.PathPlannerAuto;
import com.pathplanner.lib.controllers.*;
import com.pathplanner.lib.path.PathPlannerPath;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import frc.robot.subsystems.TestBed;

public class RobotContainer {
  private final SendableChooser<Command> autoChooser;
//init subsystem

  private TestBed m_TestBed = new TestBed();
//init new joystick on usb port 0
  private CommandJoystick driverJoystick = new CommandJoystick(0);

  public RobotContainer() {

  //apply set bindings
    configureBindings();

  //apply motor config
    m_TestBed.motorConfig();

  //set default command for driver control
    m_TestBed.setDefaultCommand(
      new RunCommand( () -> m_TestBed.drive(driverJoystick.getDirectionDegrees(), driverJoystick.getMagnitude()),
       m_TestBed));
   
     autoChooser = AutoBuilder.buildAutoChooser();
     SmartDashboard.putData("Auto Chooser", autoChooser);
   
  }

  //contorller bindings here
  private void configureBindings() {}

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
    // return new PathPlannerAuto("Example Auto");
        // Load the path you want to follow using its name in the GUI
    PathPlannerPath path = PathPlannerPath.fromPathFile("Example Path");

    // Create a path following command using AutoBuilder. This will also trigger event markers.
    return AutoBuilder.followPath(path);
  }
}

