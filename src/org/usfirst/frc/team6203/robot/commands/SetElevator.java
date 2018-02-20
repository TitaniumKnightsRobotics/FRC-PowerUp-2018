package org.usfirst.frc.team6203.robot.commands;

import org.usfirst.frc.team6203.robot.Robot;
import org.usfirst.frc.team6203.robot.subsystems.Elevator.State;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetElevator extends Command {

	State state;

	public SetElevator(State state) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.mElevator);
		this.state = state;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.mElevator.setElevatorState(this.state);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.mElevator.moveElevator();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.mElevator.onTarget();
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.mElevator.setElevatorState(State.IDLE);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
