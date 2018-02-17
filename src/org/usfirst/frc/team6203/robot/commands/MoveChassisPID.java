package org.usfirst.frc.team6203.robot.commands;

import org.usfirst.frc.team6203.robot.Robot;
import org.usfirst.frc.team6203.robot.subsystems.Chassis;

import edu.wpi.first.wpilibj.command.Command;

/**
*
*/
public class MoveChassisPID extends Command {
	
	double target;
	double current;

	boolean isFinished = false;

	public MoveChassisPID(double distance) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		this.target = distance;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.encoder.reset();
		Robot.mChassis.enablePIDControl();
		Robot.mChassis.setSetpoint(target);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.mChassis.usePIDOutput();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.mChassis.onTarget();
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.mChassis.resetPIDControl();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}