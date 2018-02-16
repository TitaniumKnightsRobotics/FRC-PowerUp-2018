package org.usfirst.frc.team6203.robot.commands;

import org.usfirst.frc.team6203.robot.Robot;
import org.usfirst.frc.team6203.robot.subsystems.Chassis;

import edu.wpi.first.wpilibj.command.Command;

/**
*
*/
public class MoveChassis extends Command {
    double targetDistance;
    double distanceRange = 3;
    double speed;
    boolean isFinished = false;
    
   public MoveChassis(double distance, double speed) {
       // Use requires() here to declare subsystem dependencies
       // eg. requires(chassis);
	   this.targetDistance = distance;
	   this.speed = speed;
   }

   // Called just before this Command runs the first time
   protected void initialize() {
       Robot.encoder.reset();
   }

   // Called repeatedly when this Command is scheduled to run
   protected void execute() {
	   double cur_distance = Robot.encoder.getDistance();
	   if (cur_distance < targetDistance) {
		   Chassis.drive.tankDrive(speed, speed);
	   }
	   else if (cur_distance > targetDistance) {
		   Chassis.drive.tankDrive(-speed, -speed);
	   }
	   
	   // stop if distance within a certain range of target distance
	   if (cur_distance > targetDistance - distanceRange && cur_distance < targetDistance + distanceRange) {
		   isFinished = true;
	   }
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