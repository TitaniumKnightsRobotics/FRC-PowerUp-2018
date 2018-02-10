package org.usfirst.frc.team6203.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team6203.robot.Robot;
import org.usfirst.frc.team6203.robot.commands.Drive;
import org.usfirst.frc.team6203.robot.commands.Move_Detect;
import org.usfirst.frc.team6203.robot.subsystems.ADIS16448_IMU;
import org.usfirst.frc.team6203.robot.subsystems.Chassis;
import org.usfirst.frc.team6203.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Ultrasonic;

import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class NewAuto extends Command {
	//Sets up variables for later assignment
	public double StartTime;
	public double Time11;
	public double DiagonalDistance;
	public int phase_stage;
	public int OptionChooser;
	public int MotorChooser;
	public double rightSpeed;
	public double leftSpeed;
    public NewAuto() {
    	
  
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//Used to determine when we start commands. 
    	//Without it timing could be thrown off if we take too long to start.
    	StartTime = System.currentTimeMillis();
    	//What case for the switch we will use. It is mainly 
    	//changed later on in the execute over time.
    	phase_stage = 1;
    	//Sets the gyro to 0.
    	Robot.imu.calibrate();
    	//Variable used after runningTime is reset.
    	Time11 = 500;
    	//Variable for the encoder for a distance. NOT YET SET MUST DO MATH
    	DiagonalDistance = 0;
    	OptionChooser = 1; 
    	//1 = left going left
    	//2 = left going right
    	//3 = middle going left
    	
    	MotorChooser = 1;
    	//1 = standard
    	//2 = inverse
    }
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//The time in milliseconds the robot has run
    	double RunningTime = System.currentTimeMillis();
    	switch(OptionChooser) {
    	case 1:
    		//if (RunningTime - StartTime < RunTime) {
    		if (Robot.encoder.getDistance() < 120) {
        		phase_stage = 1;
        	}
        	
    		else if (Robot.imu.getAngleZ() < 90 && MotorChooser == 1) {
        		phase_stage = 2;
        	}
        	
    		else if (Robot.imu.getAngleZ() > -90 && MotorChooser == 2) {
        		phase_stage = 3;
        	}
    		
    		else if (RunningTime - StartTime < Time11) {
    			phase_stage = 4;
    		}
    		//finish rest after elevator
        		
        	switch(phase_stage) {
        	case 1:
    			Chassis.drive.tankDrive(.3, .3);
    			break;
    		case 2:
    			Chassis.drive.tankDrive(.3, -.3);
    			RunningTime = StartTime;
    			break;
    		case 3:
    			Chassis.drive.tankDrive(-.3, .3);
    			RunningTime = StartTime;
    			break;
    		case 4:
    			Chassis.drive.tankDrive(.2, .2);
        	}
        	//finish rest after elevator
    	case 2:
    		if (Robot.encoder.getDistance() < 10) {
        		phase_stage = 1;
        	}
        	
    		if (Robot.imu.getAngleZ() < 60 && MotorChooser == 1 ){
        		phase_stage = 2;
        	}
        	//Turn right if on right side
        	if (Robot.imu.getAngleZ() > -60 && MotorChooser == -1 ){
        		phase_stage = 3;
        	}
        	//Move diagonally across field
        	if (Robot.encoder.getDistance() < DiagonalDistance) {
        		phase_stage = 4;
        	}
        	if (Robot.imu.getAngleZ() > -90 && MotorChooser == 1 ){
        		phase_stage = 5;
        	}
        	
        	if (Robot.imu.getAngleZ() < 90 && MotorChooser == -1 ){
        		phase_stage = 6;
        	}
        	if (RunningTime - StartTime < Time11) {
        		phase_stage = 7;
        	}
        	//Create Case for dropping block
        		
        	switch(phase_stage) {
	        	case 1:
	    			Chassis.drive.tankDrive(.3, .3);
	    			
	    			break;
	    		case 2:
	    			Chassis.drive.tankDrive(.3, -.3);
	    			RunningTime = StartTime;
	    			break;
	    		case 3:
	    			Chassis.drive.tankDrive(-.3, .3);
	    			 RunningTime = StartTime;
	    			break;
	    			
	    		case 4:
	    			Chassis.drive.tankDrive(.3, .3);
	    			break;
	    		case 5:
	    			Chassis.drive.tankDrive(-.3, .3);
	    			break;
	    		case 6:
	    			Chassis.drive.tankDrive(.3, -.3);
	    		case 7:
	    			Chassis.drive.tankDrive(.2, .2);
	    			
	    		//create case for dropping block
        	}
    	case 3:	
    		if (Robot.encoder.getDistance() < 10) {
        		phase_stage = 1;
        	}
        	
    		else if (Robot.imu.getAngleZ() > -30 && MotorChooser == 1) {
        		phase_stage = 2;
        	}
        	
    		else if (Robot.imu.getAngleZ() < 30 && MotorChooser == -1) {
        		phase_stage = 3;
        	}
    		
    		else if (Robot.imu.getAngleZ() < 0 && MotorChooser == 1) {
        		phase_stage = 4;
        	}
        	
    		else if (Robot.imu.getAngleZ() > 0 && MotorChooser == -1) {
        		phase_stage = 5;
    		}
    		else if (RunningTime - StartTime < Time11) {
    			phase_stage = 6;
    		}
        	//Needs testing to determine location after swerving
    		
    		//finish rest after elevator
        		
        	switch(phase_stage) {
        	case 1:
    			Chassis.drive.tankDrive(.3, .3);
    			break;
    		case 2:
    			Chassis.drive.tankDrive(.1, .3);
    			break;
    		case 3:
    			Chassis.drive.tankDrive(.3, .1);
    			break;
    		case 4:
    			Chassis.drive.tankDrive(.3, .1);
    			RunningTime = StartTime;
    			break;
    		case 5:
    			Chassis.drive.tankDrive(.1, .3);
    			RunningTime = StartTime;
    			break;
    		case 6:
    			Chassis.drive.tankDrive(.2, .2);
    			break;
        	}
        	//finish rest after elevator
    	}
    	
    	
    	
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