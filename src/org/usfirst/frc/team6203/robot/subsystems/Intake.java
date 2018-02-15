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

	Victor intake_motor;
	final double k = 5.0;

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

//	private double calculateError() {
//		return (master_motor.getRaw() - slave_motor.getRaw()) / k;
//	}

//	private void correctSpeed() {
//		slave_motor.set(master_motor.getSpeed() + calculateError());
//	}

	public void setIntake(State s) {

	}

}
