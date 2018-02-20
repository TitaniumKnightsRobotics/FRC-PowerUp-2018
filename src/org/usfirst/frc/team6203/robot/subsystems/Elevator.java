package org.usfirst.frc.team6203.robot.subsystems;

import org.usfirst.frc.team6203.robot.Constants;
import org.usfirst.frc.team6203.robot.Robot;
import org.usfirst.frc.team6203.robot.RobotMap;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */

public class Elevator extends PIDSubsystem {

	private static Elevator mInstance = new Elevator();
	private State state;
	public static DigitalInput limit1, limit2, limit3;

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
		limit1 = new DigitalInput(RobotMap.limit_switch_bottom);
		// l2 = new DigitalInput(1);
		// l3 = new DigitalInput(2);

		counter.setDistancePerPulse(Constants.kElevatorDistancePerPulse);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void runElevatorOpenLoop() {
		// get ben's code
		int pov = Robot.oi.driverStick.getPOV();
		if (pov != -1)
			pov /= 45;

		SmartDashboard.putNumber("pov", pov);

		boolean b1 = !limit1.get();
		// boolean b2 = l2.get();
		// boolean b3 = l3.get();

		if (pov == 0)
			elevatorMotor.set(Constants.kMaxElevatorSpeed);
		else if (pov == 1 || pov == 7)
			elevatorMotor.set(Constants.kMaxElevatorSpeed / 2);
		else if (!b1 && (pov == 3 || pov == 5))
			elevatorMotor.set(-Constants.kMaxElevatorSpeed / 2);
		else if (!b1 && (pov == 4))
			elevatorMotor.set(-Constants.kMaxElevatorSpeed);
		else
			elevatorMotor.set(0);
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
		default:
			elevatorMotor.set(0);
			break;
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
