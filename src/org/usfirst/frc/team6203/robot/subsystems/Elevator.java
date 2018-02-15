package org.usfirst.frc.team6203.robot.subsystems;

import org.usfirst.frc.team6203.robot.RobotMap;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */

public class Elevator extends Subsystem {

	public enum State {
		MAX_HEIGHT, COLLAPSED, SWITCH_HEIGHT, SCALE_HEIGHT, DISABLED;
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	private Victor elevatorMotor;
	private Counter counter; // theoretically a hall effect sensor

	public Elevator() {
		elevatorMotor = new Victor(RobotMap.elevatorMotor);
		counter = new Counter(1);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public boolean isSet() {
		return counter.get() > 0;
	}

	public void setElevatorState(State s) {
		// todo
	}

	public void reset() {
		setElevatorState(State.COLLAPSED);
		counter.reset();
		elevatorMotor.setDisabled();
	}

}
