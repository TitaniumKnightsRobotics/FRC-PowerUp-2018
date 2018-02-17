package org.usfirst.frc.team6203.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	public static Joystick driverStick, gunnerStick;

	public OI() {
		driverStick = new Joystick(RobotMap.controller);
	}

	public double getDriverStickLeftX() {
		return driverStick.getRawAxis(0);
	}

	public double getDriverStickLeftY() {
		return driverStick.getRawAxis(1);
	}

	public double getDriverStickRightX() {
		return driverStick.getRawAxis(2);
	}

	public double getDriverStickRightY() {
		return driverStick.getRawAxis(3);
	}


	public JoystickButton getButton(int joystick, int button) {

		return new JoystickButton(driverStick, button);

	}

}
