// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ElevatorSubsystem extends SubsystemBase {

    private final Spark liftMotor;

    public ElevatorSubsystem() {
        liftMotor = new Spark(2);
    }

    @Override
    public void periodic() {}

    public void setRawSpeed(double speed) {
        liftMotor.set(speed);
    }

    public void stopLift() {
        liftMotor.stopMotor();
    }
}
