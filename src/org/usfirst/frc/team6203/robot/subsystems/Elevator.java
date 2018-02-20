package org.usfirst.frc.team6203.robot.subsystems;

import org.usfirst.frc.team6203.robot.Constants;
import org.usfirst.frc.team6203.robot.Robot;
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
	private State state;

	public static Elevator getInstance() {
		return mInstance;
	}

	public enum State {
		MAX_HEIGHT, COLLAPSED, SWITCH_HEIGHT, SCALE_HEIGHT, DISABLED, IDLE;
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	private Victor elevatorMotor;
	private Counter counter; // theoretically a hall effect sensor

	public Elevator() {
		super("Elevator", 2.0, 0.0, 0.0);
		elevatorMotor = new Victor(RobotMap.elevatorMotor);
		counter = new Counter(1);
		counter.setDistancePerPulse(Constants.kElevatorDistancePerPulse);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public boolean isSet() {
		return counter.get() > 0;
	}

	public void setElevatorState(State s) {
		state = s;
	}

	public void moveElevator() {
		switch (this.state) {
		case DISABLED:
			elevatorMotor.setDisabled();
			break;
		case IDLE:
			setSetpoint(0);
			break;
		case SWITCH_HEIGHT:
			setSetpoint(Constants.kSwitchHeight);
			break;
		case SCALE_HEIGHT:
			setSetpoint(Constants.kScaleHeight);
			break;
		case MAX_HEIGHT:
		}
		enable();
	}

	public void setElevatorMotor(double s) {
		elevatorMotor.set(s);
	}

	public void reset() {
		setElevatorState(State.COLLAPSED);
		counter.reset();
		elevatorMotor.setDisabled();
	}

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return counter.getDistance();
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		elevatorMotor.pidWrite(output);
	}

}
