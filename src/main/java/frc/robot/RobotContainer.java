// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.subsystems.DriveSubsystem;
import frc.robot.utils.LogitechAttackThree;
import edu.wpi.first.wpilibj.DoubleSolenoid;
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
    
    private final LogitechAttackThree leftJoytick = new LogitechAttackThree(0);
    private final LogitechAttackThree rightJoystick = new LogitechAttackThree(1);
    
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

        // Set the default command of the drive subsystem to control the drivetrain
        driveSubsystem.setDefaultCommand(driveSubsystem.run(
            () -> {
                if(leftJoytick.buttonTwo().getAsBoolean() && leftJoytick.trigger().getAsBoolean()) {
                    driveSubsystem.drive(-(leftJoytick.getY() * driveMultiplier), -(rightJoystick.getY() * driveMultiplier));
                } else {
                    driveSubsystem.drive(rightJoystick.getY() * driveMultiplier, leftJoytick.getY() * driveMultiplier);
                }
            }
        ));


        rightJoystick.trigger().onTrue(Commands.runOnce(() -> {
            driveSubsystem.setTransmission(DoubleSolenoid.Value.kForward);
        }));

        rightJoystick.buttonTwo().onTrue(Commands.runOnce(() -> {
            driveSubsystem.setTransmission(DoubleSolenoid.Value.kReverse);
        }));
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
