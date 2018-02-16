
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
	    ultrasonic.setAutomaticMode(true);
	    
	    digit = new DigitalOutput(5);
		
		chooser.addDefault("Default Auto", new Move_Detect());
		SmartDashboard.putData("Auto Routine", chooser);

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
		SmartDashboard.putNumber("ultrasonic: ", ultrasonic.getRangeInches());

		Scheduler.getInstance().run();
	}

	@Override
	public void testPeriodic() {
		SmartDashboard.putNumber("Hall Effect", halleffect.get());
		SmartDashboard.putNumber("ultrasonic", ultrasonic.getRangeMM());

		LiveWindow.run();
	}
}
