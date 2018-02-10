
package org.usfirst.frc.team6203.robot;

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

	public static OI oi;
	public static Chassis chassis;
	public static CameraServer axisCam;
	public static CameraServer usbCam;
	public static Elevator elevator;
	public static ADIS16448_IMU imu;
	public static Encoder encoder;
	public static Counter halleffect;
	public static Ultrasonic ultra;

	public static Ultrasonic ultrasonic;
	public static DigitalOutput digit;


	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {

		// Instantiate subsystems
		oi = new OI();
		chassis = new Chassis();

		axisCam = CameraServer.getInstance();
		axisCam.addAxisCamera("test", Constants.IP);
		axisCam.startAutomaticCapture();

		usbCam = CameraServer.getInstance();
		usbCam.startAutomaticCapture();
		
		imu = new ADIS16448_IMU();
		encoder = new Encoder(RobotMap.encoder_channelA, RobotMap.encoder_channelB);
		halleffect = new Counter(RobotMap.halleffect);
		ultrasonic = new Ultrasonic(RobotMap.ultrasonic1, RobotMap.ultrasonic2);


		ultrasonic = new Ultrasonic(4,3);
	    ultrasonic.setAutomaticMode(true);

		
		chooser.addDefault("Default Auto", new Move_Detect());
		// chooser.addObject("My Auto", new MyAutoCommand());

		SmartDashboard.putData("Auto Routine", chooser);

		//Drive.slow = false;
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

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		autonomousCommand = chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
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
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();

		Drive.slow = false;

	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {

		SmartDashboard.putNumber("ultrasonic: ", ultrasonic.getRangeInches());

		SmartDashboard.putNumber("ultrasonic: ", ultra.getRangeInches());

		

		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		SmartDashboard.putNumber("Hall Effect", halleffect.get());

		SmartDashboard.putNumber("ultrasonic", ultrasonic.getRangeMM());

		SmartDashboard.putNumber("ultrasonic", ultra.getRangeMM());


		LiveWindow.run();
	}
}
