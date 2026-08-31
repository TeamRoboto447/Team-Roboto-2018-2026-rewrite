// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.utils;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/** Add your docs here. */
public class TriggerJoystick {

    private Joystick joystick;
    private ArrayList<JoystickButton> buttons = new ArrayList<>();

    public TriggerJoystick(Joystick inputJoystick) {
        this.joystick = inputJoystick;

        enumerateButtons();
    }

    public TriggerJoystick(int port) {
        this.joystick = new Joystick(port);

        enumerateButtons();
    }

    public JoystickButton getButton(int buttonId) {
        return buttons.get(buttonId-1);
    }

    public boolean getButtonState(int buttonId) {
        return getButton(buttonId).getAsBoolean();
    }

    public double getX() {
        return joystick.getX();
    }

    public double getY() {
        return joystick.getY();
    }

    private void enumerateButtons() {
        for (int i = 1; i <= this.joystick.getButtonCount(); i++) {
            this.buttons.add(new JoystickButton(this.joystick, i-1));
        }

        
    }
}
