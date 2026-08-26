// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Tajiri}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
    PowerDistribution PDP = new PowerDistribution(0, ModuleType.kCTRE);
    public final DriveSubsystem driveSubsystem;

    // Replace with CommandPS4Controller or CommandJoystick if needed
    private final Joystick leftStick = new Joystick(0);
    private final Joystick rightStick = new Joystick(1);
    
	static double driveMultiplier = 1;

    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
        driveSubsystem = new DriveSubsystem();
        // Configure the trigger bindings
        configureBindings();
    }

    /**
     * Use this method to define your trigger->command mappings. Triggers can be created via the
     * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
     * predicate, or via the named factories in {@link
     * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
     * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
     * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
     * joysticks}.
     */
    private void configureBindings() {
        driveSubsystem.setDefaultCommand(driveSubsystem.run(
            () -> {
                if(leftStick.getRawButton(1)&&leftStick.getRawButton(2)) {
                    driveSubsystem.drive(-(leftStick.getY() * driveMultiplier), -(rightStick.getY() * driveMultiplier));
                } else {
                    driveSubsystem.drive(rightStick.getY() * driveMultiplier, leftStick.getY() * driveMultiplier);
                }
            }
        ));
    }

    public void teleopInit() {
        driveSubsystem.resetEncoders();
    }

    public void teleopPeriodic() {
        driveSubsystem.setSafety(true);
        SmartDashboard.putData("PDP", PDP);
    }

    /**
     * Use this to pass the autonomous command to the main {@link Tajiri} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        return Commands.none();
    }
}
