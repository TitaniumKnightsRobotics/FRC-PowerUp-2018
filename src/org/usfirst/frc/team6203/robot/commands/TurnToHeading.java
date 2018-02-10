package org.usfirst.frc.team6203.robot.commands;

import org.usfirst.frc.team6203.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnToHeading extends Command {

	private double target;
	private boolean dir = false;

	public TurnToHeading(double heading) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.chassis);
		target = heading;

	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.imu.calibrate();
		dir = target <= 180;

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		while (Robot.imu.getAngle() != target) {
			Robot.chassis.turn(0.2, dir);
		}
		
		isFinished();

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return true;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
