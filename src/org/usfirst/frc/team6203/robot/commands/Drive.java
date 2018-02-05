package org.usfirst.frc.team6203.robot.commands;

import org.usfirst.frc.team6203.robot.Robot;
import org.usfirst.frc.team6203.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Drive extends Command {

	public static boolean slow;
	public boolean slow_pressed = false;
	
	public Drive() {
		requires(Robot.chassis);
	}

	protected void initialize() {
	}

	private void updateButtons(){
		boolean slow_curr = Robot.oi.driverStick.getRawButton(RobotMap.slowspeed);
		if (!slow_pressed && slow_curr)
			slow = !slow;
		
		slow_pressed = slow_curr;
		
		
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		updateButtons();
		
		Robot.chassis.tankDrive();
//		Robot.chassis.arcadeDrive();
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