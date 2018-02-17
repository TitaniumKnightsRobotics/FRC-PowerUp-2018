package org.usfirst.frc.team6203.robot.commands;

import org.usfirst.frc.team6203.robot.Robot;
import org.usfirst.frc.team6203.robot.subsystems.Chassis;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
*
*/
public class Turn extends Command {
	public double targetDegrees;
	public double speed; // 0 to 1
	public double degreeRange = 10;
	public double degreeThreshold = .5;
	public double cur_angle;
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
		Robot.imu.reset();
		Robot.imu.calibrate();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		cur_angle = Robot.imu.getAngleZ();
		SmartDashboard.putNumber("cur_angle", cur_angle);
		SmartDashboard.putNumber("targetDegrees", targetDegrees);

		double newSpeed = (cur_angle - targetDegrees < 0 ? 1 : -1) * speed
				* Math.pow(Math.abs((cur_angle - targetDegrees) / targetDegrees), 1);
		if (newSpeed >= 0) {
			newSpeed = newSpeed < minSpeed ? minSpeed : newSpeed;
		} else {
			newSpeed = newSpeed > -minSpeed ? -minSpeed : newSpeed;
		}
		Robot.mChassis.tankDrive(newSpeed, -newSpeed);
		SmartDashboard.putNumber("newSpeed", newSpeed);

		if (cur_angle > targetDegrees - degreeThreshold && cur_angle < targetDegrees + degreeThreshold) {
			Robot.mChassis.tankDrive(0, 0);
			isFinished = true;
		}

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		SmartDashboard.putBoolean("isFinished", isFinished);
		return isFinished;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.encoder.reset();
		Robot.imu.reset();
		Robot.imu.calibrate();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
