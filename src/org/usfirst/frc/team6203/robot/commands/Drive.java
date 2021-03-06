package org.usfirst.frc.team6203.robot.commands;

import org.usfirst.frc.team6203.robot.Constants;
import org.usfirst.frc.team6203.robot.Robot;
import org.usfirst.frc.team6203.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Drive extends Command {

	public static boolean slow;
	public boolean slow_pressed = false;

	public Drive() {
		requires(Robot.mChassis);
	}

	protected void initialize() {
		slow = false;
	}

	private void updateButtons() {
		boolean slow_curr = Robot.oi.driverStick.getRawButton(2);
		if (!slow_pressed && slow_curr)
			slow = !slow;

		slow_pressed = slow_curr;

		Robot.digital_output.set(Robot.oi.driverStick.getRawButton(1));
		SmartDashboard.putBoolean("BUTTON THING", Robot.oi.driverStick.getRawButton(1));
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		updateButtons();

		Robot.mChassis.tankDrive();
		// Robot.chassis.arcadeDrive();
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
