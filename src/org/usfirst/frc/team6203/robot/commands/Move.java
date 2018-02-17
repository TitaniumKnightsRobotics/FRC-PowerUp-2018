package org.usfirst.frc.team6203.robot.commands;

import org.usfirst.frc.team6203.robot.Constants;
import org.usfirst.frc.team6203.robot.Robot;
import org.usfirst.frc.team6203.robot.subsystems.Chassis;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
*
*/
public class Move extends Command {

	double target;
	double current;

	boolean isFinished = false;

	private double P, I, D = 1;

	private double error = 0;
	private double p_error = 0;
	private double scaled_error = 0;
	private double scale = 0.001;

	public Move(double distance) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		this.target = distance;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.resetSensors();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		current = Robot.encoder.getDistance();
		error = target - current;
		scaled_error = error * scale;
		P = Constants.kDriveTrainP * scaled_error;
		I += Constants.kDriveTrainI * scaled_error;
		D = (scaled_error - p_error) / Constants.kDriveTrainD;

		double output = P + I + D;

		SmartDashboard.putNumber("cur_angle", Robot.imu.getAngleZ());
		SmartDashboard.putNumber("output", output);
		SmartDashboard.putNumber("encoder distance", Robot.encoder.getDistance());
//		Robot.chassis.tankDrive(output, output);
		double theta = 0 - Robot.imu.getAngleZ();
		double correction = theta * 0.035;
		SmartDashboard.putNumber("correction", correction);
		if (theta < 0)
			Robot.mChassis.tankDrive(output + correction, output);
		else if (theta > 0)
			Robot.mChassis.tankDrive(output, output + correction);
		else
			Robot.mChassis.tankDrive(output, output);

		if (Math.abs(error) < 0.1)
			isFinished = true;

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