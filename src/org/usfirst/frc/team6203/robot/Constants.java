package org.usfirst.frc.team6203.robot;

public class Constants {

	// IP
	public static final String IP = "10.62.3.52";

	// Drive Constants
	public static final double kSlow_multiplier = 0.6;
	public static final double kDistancePerPulse = Math.PI * 4;
	public static final double kMaxMotorOutput = 0.6;

	// Chassis PID control
	public static final double kDriveTrainP = 1.1;
	public static final double kDriveTrainI = 0.016;
	public static final double kDriveTrainD = 0.8;

	// Intake
	public static final double kIntakeSpeed = 0.6;
	public static final double kDepositSpeed = 0.5;
	public static final double kIntakeP = 0.3;
	public static final double kInSpeed = -kDepositSpeed;

	// Elevator
	public static final double kElevatorRatio = 100;
	public static final double kElevatorSpeed = 0.2;
	public static final double kElevatorDistancePerPulse = Math.PI * (1.432 / kElevatorRatio); // how far chain moves each revolution
	public static final double kSwitchHeight = 0;
	public static final double kScaleHeight = 0;
	public static final double kMaxHeight = 0;

}
