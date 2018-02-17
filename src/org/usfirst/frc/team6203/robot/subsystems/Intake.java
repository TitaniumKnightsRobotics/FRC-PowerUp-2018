package org.usfirst.frc.team6203.robot.subsystems;

import org.usfirst.frc.team6203.robot.RobotMap;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	private static Intake mInstance = new Intake();

	public static Intake getInstance() {
		return mInstance;
	}

	private Victor intake_motor;

	public enum State {
		INTAKE, DEPOSIT, IDLE, DISABLED;
	}

	public Intake() {
		intake_motor = new Victor(RobotMap.intakeMotor);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void setIntake(State s) {

		// todo

	}

}
