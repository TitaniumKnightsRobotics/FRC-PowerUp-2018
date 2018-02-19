package org.usfirst.frc.team6203.robot.commands;

import org.usfirst.frc.team6203.robot.Robot;
import org.usfirst.frc.team6203.robot.subsystems.Chassis;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
*
*/
public class Turn extends Command {
	
	private double targetDegrees;
	private double speed; // 0 to 1
	private double degreeThreshold = .5;
	private double cur_angle;
	boolean isFinished = false;
	private final double minSpeed = 0.55;

	public Turn(double degrees, double speed) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.mChassis);
		this.targetDegrees = degrees;
		this.speed = speed;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.resetSensors();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		cur_angle = Robot.imu.getAngleZ();
		SmartDashboard.putNumber("cur_angle", cur_angle);
		SmartDashboard.putNumber("targetDegrees", targetDegrees);
		
		int direction = cur_angle - targetDegrees < 0 ? 1 : -1;
		double newSpeed = direction * speed * Math.abs((cur_angle - targetDegrees) / targetDegrees);
		if (newSpeed >= 0) {
			newSpeed = newSpeed < minSpeed ? minSpeed : newSpeed;
		} else {
			newSpeed = newSpeed > -minSpeed ? -minSpeed : newSpeed;
		}
		Robot.mChassis.tankDrive(newSpeed, -newSpeed);

		if (cur_angle > targetDegrees - degreeThreshold && cur_angle < targetDegrees + degreeThreshold) {
			Robot.mChassis.tankDrive(0, 0);
			isFinished = true;
		}

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return isFinished;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.resetSensors();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
