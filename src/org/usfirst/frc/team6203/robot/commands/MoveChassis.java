package org.usfirst.frc.team6203.robot.commands;

import org.usfirst.frc.team6203.robot.Robot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team6203.robot.subsystems.Chassis;

import edu.wpi.first.wpilibj.command.Command;

/**
*
*/
public class MoveChassis extends Command {
    double targetDistance;
    double distanceRange = .5;
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
	   SmartDashboard.putNumber("kDistancePerPulse", Robot.encoder.getRate());
	   double cur_distance = Robot.encoder.getDistance();
	   SmartDashboard.putNumber("cur_distance", cur_distance);
	   SmartDashboard.putNumber("target_distance", targetDistance);
	   
	   double newSpeed = speed * Math.pow(((targetDistance - cur_distance) / cur_distance), 3);
	   
	   if (cur_distance < targetDistance) {
		    //Chassis.drive.tankDrive(speed, speed);
//		   Robot.chassis.simpleDrive(speed);
		   Robot.chassis.tankDrive(newSpeed, newSpeed);
		   SmartDashboard.putBoolean("runs", true);
	   }
	   else if (cur_distance > targetDistance) {
		    //Chassis.drive.tankDrive(-speed, -speed);
//		   Robot.chassis.simpleDrive(-speed);
		   Robot.chassis.tankDrive(-newSpeed, -newSpeed);
		   SmartDashboard.putBoolean("runs2", true);
	   }
	   
	   // stop if distance within a certain range of target distance
	   if (cur_distance > targetDistance - distanceRange && cur_distance < targetDistance + distanceRange) {
		   isFinished = true;
		   SmartDashboard.putBoolean("runs", false);
	   }
	   SmartDashboard.putNumber("Left motor", Robot.chassis.leftMotor.get());
	   SmartDashboard.putNumber("Right motor", Robot.chassis.rightMotor.get());
	   SmartDashboard.putNumber("Encoder raw", Robot.encoder.get());
   }

   // Make this return true when this Command no longer needs to run execute()
   protected boolean isFinished() {
	   SmartDashboard.putBoolean("IsFinished", isFinished);
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