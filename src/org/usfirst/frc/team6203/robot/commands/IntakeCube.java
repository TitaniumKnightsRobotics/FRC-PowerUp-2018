package org.usfirst.frc.team6203.robot.commands;

import org.usfirst.frc.team6203.robot.Robot;
import org.usfirst.frc.team6203.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeCube extends Command {

	private boolean isFinished = false;
	private Intake.State state;

	public IntakeCube(Intake.State state) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		this.state = state;

	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.mIntake.setIntakeState(this.state);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.mIntake.runIntakeLoop();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.mIntake.setIntakeState(Intake.State.IDLE);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
