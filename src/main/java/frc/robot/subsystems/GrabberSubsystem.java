// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class GrabberSubsystem extends SubsystemBase {
    private final Spark grabberMotor;

    private final DigitalInput grabStop;

    private final DoubleSolenoid grabberArmExt;
    private final DoubleSolenoid grabberArmGrab;

  /** Creates a new GrabberSubsystem. */
  public GrabberSubsystem() {
    grabberMotor = new Spark(3);

    grabStop = new DigitalInput(6);

	grabberArmExt = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 2, 3);
	grabberArmGrab = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 4, 5);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void extendArm() {
    grabberArmExt.set(DoubleSolenoid.Value.kForward);
  }

  public void retractArm() {
    grabberArmExt.set(DoubleSolenoid.Value.kReverse);
  }

  public void closeGrabber() {
    grabberArmGrab.set(DoubleSolenoid.Value.kForward);
  }

  public void openGrabber() {
    grabberArmGrab.set(DoubleSolenoid.Value.kReverse);
  }
}
