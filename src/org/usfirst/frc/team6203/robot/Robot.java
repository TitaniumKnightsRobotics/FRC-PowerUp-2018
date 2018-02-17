
package org.usfirst.frc.team6203.robot;

import org.usfirst.frc.team6203.robot.commands.Auto;
import org.usfirst.frc.team6203.robot.commands.Drive;
import org.usfirst.frc.team6203.robot.subsystems.ADIS16448_IMU;
import org.usfirst.frc.team6203.robot.subsystems.Chassis;
import org.usfirst.frc.team6203.robot.subsystems.Elevator;
import org.usfirst.frc.team6203.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	//Subsystems
	public static Chassis mChassis = Chassis.getInstance();
	public static Elevator mElevator = Elevator.getInstance();
	public static Intake mIntake = Intake.getInstance();

	//Camera and OI
	public static OI oi;
	public static CameraServer axisCam;
	public static CameraServer usbCam;
	
	//Sensors
	public static ADIS16448_IMU imu;
	public static Encoder encoder;
	public static Counter halleffect;
	public static Ultrasonic ultrasonic;
	
	//LED Strip
	public static DigitalOutput digital_output;

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {

		oi = new OI();
		axisCam = CameraServer.getInstance();
		axisCam.addAxisCamera("Axis Camera", Constants.IP);
		axisCam.startAutomaticCapture();

		usbCam = CameraServer.getInstance();
		usbCam.startAutomaticCapture();
		
		imu = new ADIS16448_IMU();
		encoder = new Encoder(RobotMap.encoder_channelA, RobotMap.encoder_channelB);
		
		halleffect = new Counter(RobotMap.halleffect);
		
		ultrasonic = new Ultrasonic(RobotMap.ultrasonic1, RobotMap.ultrasonic2);
	    ultrasonic.setAutomaticMode(true);
	    
	    digital_output = new DigitalOutput(5);
	    
	    chooser.addDefault("Auto1", new Auto());
//		chooser.addObject("Auto2", new Auto(2, 0));
//		chooser.addObject("Auto3", new Auto(3, 0));
//		chooser.addObject("Auto1_I", new Auto(1, 1));
//		chooser.addObject("Auto2_I", new Auto(2, 1));
//		chooser.addObject("Auto3_I", new Auto(3, 1));
		SmartDashboard.putData("Auto Routine: ", chooser);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		autonomousCommand = chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		if (autonomousCommand != null)
			autonomousCommand.cancel();

		Drive.slow = false;
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
