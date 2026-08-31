// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.utils;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/** Add your docs here. */
public class LogitechAttackThree {

    private Joystick joystick;

    public LogitechAttackThree(Joystick inputJoystick) {
        this.joystick = inputJoystick;
    }

    public LogitechAttackThree(int port) {
        this.joystick = new Joystick(port);
    }

    public double getX() {
        return joystick.getX();
    }

    public double getY() {
        return joystick.getY();
    }

    public JoystickButton trigger() {
        return new JoystickButton(joystick, 1);
    }

    public JoystickButton buttonTwo() {
        return new JoystickButton(joystick, 2);
    }
}
