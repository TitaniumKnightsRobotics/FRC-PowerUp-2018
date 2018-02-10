package org.usfirst.frc.team6203.robot.subsystems;

import org.usfirst.frc.team6203.robot.OI;
import org.usfirst.frc.team6203.robot.Robot;
import org.usfirst.frc.team6203.robot.RobotMap;
import org.usfirst.frc.team6203.robot.Robot;
import org.usfirst.frc.team6203.robot.commands.Drive;
import org.usfirst.frc.team6203.robot.subsystems.ADIS16448_IMU;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Chassis extends Subsystem {

	private Victor leftMotor;
	private Victor rightMotor;
	private DifferentialDrive drive;

	private final double root2 = Math.sqrt(2);
	private final double sin135 = root2 / 2;
	private final double cos135 = -root2 / 2;
	private final double slow_multiplier = 0.6;

	public Chassis() {
		leftMotor = new Victor(RobotMap.leftMotor);
		rightMotor = new Victor(RobotMap.rightMotor);
		drive = new DifferentialDrive(leftMotor, rightMotor);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new Drive());
	}

	public void simpleDrive(double speed) {
		leftMotor.set(speed);
		rightMotor.set(speed);
	}

	public void turn(double speed, boolean d) {
		if (d) {
			leftMotor.set(-speed);
			rightMotor.set(speed);
		} else {
			leftMotor.set(speed);
			rightMotor.set(-speed);
		}
	}

	public void tankDrive() {
		SmartDashboard.putString("type", "tank");
		SmartDashboard.putNumber("axisY", OI.driverStick.getY());
		SmartDashboard.putNumber("axisX", OI.driverStick.getX());

		double x = Robot.oi.driverStick.getX();
		double y = Robot.oi.driverStick.getY();

		if (Drive.slow) {
			x *= slow_multiplier;
			y *= slow_multiplier;
		}

		double b = cos135 * x - sin135 * y;
		double a = sin135 * x + cos135 * y;

		drive.tankDrive(a, b);
	}

	public void arcadeDrive() {

		double mag = Robot.oi.driverStick.getMagnitude();
		double dir = Robot.oi.driverStick.getDirectionDegrees() / 180 - 1;

		if (Drive.slow) {
			mag *= slow_multiplier;
			dir *= slow_multiplier;
		}

		SmartDashboard.putNumber("magnitude", mag);
		SmartDashboard.putNumber("direction", dir);

		drive.arcadeDrive(mag, dir);

	}

}
