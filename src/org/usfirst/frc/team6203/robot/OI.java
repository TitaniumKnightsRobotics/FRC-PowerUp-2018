package org.usfirst.frc.team6203.robot;

import org.usfirst.frc.team6203.robot.commands.IntakeCube;
import org.usfirst.frc.team6203.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	public static Joystick driverStick;
	public static Button button1;
	public static Button button2;
	public static Button button3;
	public static Button button4;
	public static Button button5;
	
	public JoystickButton getButton(int button) {
		return new JoystickButton(driverStick, button);
	}

	public OI() {
		driverStick = new Joystick(RobotMap.controller);
		button1 = getButton(1);
		button2 = getButton(2);
		button3 = getButton(3);
		button4 = getButton(4);
		button5 = getButton(5);
		
		
		//TODO: Map commands to buttons
		button3.whenPressed(new IntakeCube(Intake.State.DEPOSIT));
		button4.whenPressed(new IntakeCube(Intake.State.INTAKE));
		
	}	
	
	//Axis values
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


	

}
