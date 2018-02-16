package org.usfirst.frc.team6203.robot.commands;

import org.usfirst.frc.team6203.robot.Robot;
import org.usfirst.frc.team6203.robot.subsystems.Chassis;

import edu.wpi.first.wpilibj.command.Command;

/**
*
*/
public class TurnChassis extends Command {
    public double targetDegrees;
    public double speed;	// 0 to 1
    public double degreeRange = 10;
    public double cur_angle;
    boolean isFinished = false;
    
   public TurnChassis(double degrees, double speed) {
       // Use requires() here to declare subsystem dependencies
       // eg. requires(chassis);
       requires(Robot.chassis);
       this.targetDegrees = degrees;
       this.speed = speed;
   }

   // Called just before this Command runs the first time
   protected void initialize() {
       Robot.imu.calibrate();
   }

   // Called repeatedly when this Command is scheduled to run
   protected void execute() {
       cur_angle = Robot.imu.getAngleZ();
       if (cur_angle < targetDegrees) {
           Chassis.drive.tankDrive(speed, -speed);
       }
       else if (cur_angle > targetDegrees) {
           Chassis.drive.tankDrive(-speed, speed);
       }
       // Robot will stop if it is within a certain range of the target
       if (cur_angle > targetDegrees - degreeRange && cur_angle < targetDegrees + degreeRange) {
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
 