package org.usfirst.frc.team6203.robot.subsystems;

import org.usfirst.frc.team6203.robot.RobotMap;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */

public class Elevator extends PIDSubsystem {

	private static Elevator mInstance = new Elevator();

	public static Elevator getInstance() {
		return mInstance;
	}

	public enum State {
		MAX_HEIGHT, COLLAPSED, SWITCH_HEIGHT, SCALE_HEIGHT, DISABLED;
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	private Victor elevatorMotor;
	private Counter counter; // theoretically a hall effect sensor

	public Elevator() {
		super("Elevator", 2.0, 0.0, 0.0);
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

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		elevatorMotor.pidWrite(output);
	}

}
