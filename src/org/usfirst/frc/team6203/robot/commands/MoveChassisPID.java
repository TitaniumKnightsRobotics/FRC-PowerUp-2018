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

	private final double kP = 0.3;
	private final double kI = 0.2;
	private final double kD = 0.3;

	private double P, I, D = 1;

	private double p_error = 0;

	public MoveChassisPID(double distance) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		this.target = distance;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.encoder.reset();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		current = Robot.encoder.getDistance();
		double error = target - current;
		P = kP * error;
		I += kI * error;
		D = (error - p_error) / kD;

		double output = P + I + D;

		Robot.chassis.tankDrive(output, output);

		if (error == 0)
			isFinished = true;

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return isFinished;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}