package org.usfirst.frc.team6203.robot.subsystems;

import org.usfirst.frc.team6203.robot.Constants;
import org.usfirst.frc.team6203.robot.RobotMap;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Intake extends Subsystem {

	// TODO: Implement PID control for motors

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	private static Intake mInstance = new Intake();

	public static Intake getInstance() {
		return mInstance;
	}

	private Spark intake_master;
	private Spark intake_slave;
	private State state;

	public enum State {
		READY, DEPOSIT, IDLE, DISABLED, OCCUPIED, SPINNING, INTAKE;
	}

	public Intake() {
		intake_master = new Spark(RobotMap.intakeMotorM);
		intake_slave = new Spark(RobotMap.intakeMotorS);
		state = State.DISABLED;
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void setIntakeState(State s) {
		state = s;
	}

	public void runIntakeLoop() {
		switch (this.state) {
		case IDLE:
			setIntakeMotor(0);
			break;
		case DISABLED:
			setIntakeMotor(0);
			intake_master.setDisabled();
			intake_slave.setDisabled();
			break;
		case SPINNING:
			setIntakeMotor(Constants.kIntakeSpeed);
			break;
		case READY:
			setIntakeMotor(Constants.kIntakeSpeed);
			break;
		case OCCUPIED:
			setIntakeMotor(0);
			break;
		case DEPOSIT:
			setIntakeMotor(Constants.kDepositSpeed);
			break;
		case INTAKE:
			setIntakeMotor(Constants.kIntakeSpeed);
			break;
		default:
			this.state = State.IDLE;
			break;
		}
	}

	public void safetyLoop() {
	}

	public double getPIDOutput() {
		return Constants.kIntakeP * (intake_master.getSpeed() - intake_slave.getSpeed());
	}

	public void setIntakeMotor(double s) {
		intake_master.set(s);
		intake_slave.set(s);
	}

	public boolean isReadyforIntake() {
		return Math.abs(Constants.kIntakeSpeed - intake_master.getSpeed()) < 0.2;
	}

	public boolean isOccupied() {
		return false;
	}

	public void publishValues() {
		SmartDashboard.putNumber("v_IntakeM", intake_master.getSpeed());
		SmartDashboard.putNumber("v_IntakeS", intake_slave.getSpeed());
	}

}
