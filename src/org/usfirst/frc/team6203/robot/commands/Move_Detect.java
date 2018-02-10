package org.usfirst.frc.team6203.robot.commands;

import org.usfirst.frc.team6203.robot.Robot;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Move_Detect extends Command {
	// sets up driving for Tank drive.

	public Move_Detect() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.chassis);

	}

	// Called just before this Command runs the first time
	protected void initialize() {
		// set up the direction the robot faces, currently 0
		Robot.imu.calibrate();
		Robot.encoder.setDistancePerPulse(4 * Math.PI);

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		// Move out the Door
		do {
			Robot.chassis.drive.tankDrive(.3, .3);
			SmartDashboard.putNumber("distance", Robot.encoder.getDistance());
		} while (Robot.encoder.getDistance() > -60);

		// Turn Left 90 degrees
		do {
			Robot.chassis.drive.tankDrive(0, .3);
		} while (Robot.imu.getAngleZ() > -90);
		Robot.imu.reset();

		// Move forward 2.5 ft
		do {
			Robot.chassis.drive.tankDrive(.3, .3);
		} while (Robot.encoder.getDistance() > -30);

		// Turn Left 90 degrees
		do {
			Robot.chassis.drive.tankDrive(0, .3);
		} while (Robot.imu.getAngleZ() > -90);
		Robot.imu.reset();

		// Move to wall
		do {
			Robot.chassis.drive.tankDrive(.2, .2);
		} while (Robot.ultrasonic.getRangeInches() > 5);

		// turn Right 90 degrees
		do {
			Robot.chassis.drive.tankDrive(.3, 0);
		} while (Robot.imu.getAngleZ() < 90);
		Robot.imu.reset();

		// Move to just before backpack
		do {
			Robot.chassis.drive.tankDrive(.2, .2);
		} while (Robot.ultrasonic.getRangeInches() < 5);

		// touch backpack
		do {
			Robot.chassis.drive.tankDrive(.1, .1);
		} while (Robot.encoder.getDistance() > -5);

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
