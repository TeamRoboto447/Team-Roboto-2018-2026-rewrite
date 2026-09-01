// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSubsystem extends SubsystemBase {
    private final Spark rightMotors;
    private final Spark leftMotors;

    private final DifferentialDrive diffDrive;

    private final Encoder rightDriveEncoder;
    private final Encoder leftDriveEncoder;

    private final DoubleSolenoid transmission;

    double rDrvEncSpd;
	double lDrvEncSpd;
    
    /** Creates a new DriveSubsystem. */
    public DriveSubsystem() {
        this.rightMotors = new Spark(0);
        this.rightMotors.setInverted(true);
        this.leftMotors = new Spark(1);

        this.diffDrive = new DifferentialDrive(leftMotors, rightMotors);

        this.rightDriveEncoder = new Encoder(0, 1);
        this.leftDriveEncoder = new Encoder(2, 3);

        this.transmission = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 0, 1);
    }

    @Override
    public void periodic() {
        SmartDashboard.putData("Drive/Differential Drive", diffDrive);
        SmartDashboard.putData("Drive/Left Encoder", leftDriveEncoder);
        SmartDashboard.putData("Drive/Right Encoder", rightDriveEncoder);
        SmartDashboard.putData("Drive/Transmission", transmission);
    }

    public void resetEncoders() {
        rightDriveEncoder.reset();
        leftDriveEncoder.reset();
    }

    public void setSafety(boolean safetyEnabled) {
        diffDrive.setSafetyEnabled(safetyEnabled);
    }

    public void drive(double leftSpeed, double rightSpeed) {
        diffDrive.tankDrive(leftSpeed, rightSpeed);
    }

    public void stop() {
        diffDrive.tankDrive(0,0);
    }

    public void setTransmission(DoubleSolenoid.Value state) {
        transmission.set(state);
    }
}