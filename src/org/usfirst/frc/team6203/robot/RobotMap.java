package org.usfirst.frc.team6203.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	//Drivetrain Motors
	public static int leftMotorF = 2;
	public static int leftMotorB = 3;
	public static int rightMotorF = 0;
	public static int rightMotorB = 1;
	
	//Controllers
	public static int controller = 0;
	
	public static int elevatorMotor = 4;

	public static int intakeMotorM = 5;
	public static int intakeMotorS = 6;

	public static int encoder_channelA = 0;
	public static int encoder_channelB = 1;
	public static int halleffect = 2;
	
	public static int ultrasonic1 = 3;
	public static int ultrasonic2 = 4;
	
	public static int limit_switch_bottom = 4;
	public static int limit_switch_top = 5;
	
	
	
}
