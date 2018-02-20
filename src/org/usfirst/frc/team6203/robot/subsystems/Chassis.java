package org.usfirst.frc.team6203.robot.subsystems;

import org.usfirst.frc.team6203.robot.Constants;
import org.usfirst.frc.team6203.robot.OI;
import org.usfirst.frc.team6203.robot.RobotMap;
import org.usfirst.frc.team6203.robot.commands.Drive;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Chassis extends Subsystem {

	private static Chassis mInstance = new Chassis();

	public static Chassis getInstance() {
		return mInstance;
	}

	public enum State {
		TELEOP, AUTO, IDLE, DISABLED;
	}

	// Motors
	public static Victor leftMotorF;
	public static Victor leftMotorB;
	public static Victor rightMotorF;
	public static Victor rightMotorB;

	public static SpeedControllerGroup m_left;
	public static SpeedControllerGroup m_right;

	// Drive control
	public static DifferentialDrive drive;

	// State
	private State state;

	public Chassis() {
		leftMotorF = new Victor(RobotMap.leftMotorF);
		leftMotorB = new Victor(RobotMap.leftMotorB);
		rightMotorF = new Victor(RobotMap.rightMotorF);
		rightMotorB = new Victor(RobotMap.rightMotorB);

		m_left = new SpeedControllerGroup(leftMotorF, leftMotorB);
		m_right = new SpeedControllerGroup(rightMotorF, rightMotorB);

		drive = new DifferentialDrive(m_left, m_right);

		drive.setMaxOutput(Constants.kMaxMotorOutput);
		drive.setSafetyEnabled(true);

		state = State.DISABLED;
	}

	public void initDefaultCommand() {
		setDefaultCommand(new Drive());
	}

	public void tankDrive(double a, double b) {
		drive.tankDrive(a, b);
	}

	public void tankDrive() {
		SmartDashboard.putString("type", "tank");
		SmartDashboard.putNumber("axisY", OI.driverStick.getY());
		SmartDashboard.putNumber("axisX", OI.driverStick.getX());

		double x = OI.driverStick.getX();
		double y = OI.driverStick.getY();

		if (Drive.slow) {
			x *= Constants.kSlow_multiplier;
			y *= Constants.kSlow_multiplier;
		}

		double b = (-Math.sqrt(2) / 2) * x - (Math.sqrt(2) / 2) * y;
		double a = (Math.sqrt(2) / 2) * x + (-Math.sqrt(2) / 2) * y;

		drive.tankDrive(a, b);
	}

	public void arcadeDrive() {

		double mag = OI.driverStick.getMagnitude();
		double rot = OI.driverStick.getDirectionDegrees() / 180 - 1;

		if (Drive.slow) {
			mag *= Constants.kSlow_multiplier;
			rot *= Constants.kSlow_multiplier;
		}

		SmartDashboard.putNumber("Joystick_mag", mag);
		SmartDashboard.putNumber("Joystick_rot", rot);

		drive.arcadeDrive(mag, rot);

	}

	public void arcadeDrive(double mag, double rot) {
		drive.arcadeDrive(mag, rot);
	}

	public void setState(State s) {
		this.state = s;
	}

	public void publishValues() {
		SmartDashboard.putNumber("l_motor_PWM", m_left.get());
		SmartDashboard.putNumber("r_motor_PWM", m_right.get());
		SmartDashboard.putString("state", this.state.toString());
	}

}
