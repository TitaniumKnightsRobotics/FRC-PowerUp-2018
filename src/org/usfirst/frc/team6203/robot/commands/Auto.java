package org.usfirst.frc.team6203.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team6203.robot.commands.Drive;
import org.usfirst.frc.team6203.robot.commands.Turn;

import org.usfirst.frc.team6203.robot.subsystems.ADIS16448_IMU;
import org.usfirst.frc.team6203.robot.subsystems.Chassis;
import org.usfirst.frc.team6203.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
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
 *
 */
public class Auto extends CommandGroup {

	public Auto(int optionChooser, int inverse) {
		//addSequential(new TurnChassis(180, 0.6));
//		addSequential(new MoveChassisPID(200));
//		addSequential(new TurnChassis(-180, 0.8));
//		addSequential(new Wait(10));
		addSequential(new Move(200));
//		addSequential(new Wait(3));
//		addSequential(new TurnChassis(-180, 0.8));
//		addSequential(new Wait(3));
//		addSequential(new MoveChassisPID(200));
		// addSequential(new TurnChassis(-90, 0.7));
		// addSequential(new MoveChassis(24, 0.7));
		//
		// if (optionChooser == 1) {
		// if (inverse == 1) {
		// addSequential(new MoveChassis(168, 0.6));
		// addSequential(new TurnChassis(90, 0.6));
		// addSequential(new MoveChassis(12, 0.6));
		// //addSequential(new ); Need elevator
		// }
		// else {
		// addSequential(new MoveChassis(168, 0.6));
		// addSequential(new TurnChassis(-90, 0.6));
		// addSequential(new MoveChassis(12, 0.6));
		// //addSequential(new ); Need elevator
		// }
		// }
		//
		//
		// else if (optionChooser == 2) {
		// if (inverse == 1) {
		// addSequential(new MoveChassis(126, 0.6));
		// addSequential(new MoveChassis(-76, 0.6));
		// addSequential(new TurnChassis(90, 0.6));
		// addSequential(new MoveChassis(90, 0.6));
		// addSequential(new TurnChassis(90, 0.6));
		// addSequential(new MoveChassis(50, 0.6));
		// //addSequential(new ); Need elevator
		// addSequential(new MoveChassis(-50, 0.6));
		// addSequential(new TurnChassis(180, 0.6));
		// addSequential(new MoveChassis(70, 0.6));
		// addSequential(new TurnChassis (90, 0.6));
		// addSequential(new MoveChassis(24, 0.6));
		// //addSequential(new ); Need elevator
		//
		// }
		// else {
		// addSequential(new MoveChassis(126, 0.6));
		// addSequential(new MoveChassis(-76, 0.6));
		// addSequential(new TurnChassis(-90, 0.6));
		// addSequential(new MoveChassis(174, 0.6));
		// addSequential(new TurnChassis(-90, 0.6));
		// addSequential(new MoveChassis(50, 0.6));
		// //addSequential(new ); Need elevator
		// addSequential(new MoveChassis(-50, 0.6));
		// addSequential(new TurnChassis(180, 0.6));
		// addSequential(new MoveChassis(70, 0.6));
		// addSequential(new TurnChassis (90, 0.6));
		// addSequential(new MoveChassis(24, 0.6));
		// //addSequential(new ); Need elevator
		//
		// }
		//
		// }
		//
		//
		// else if (optionChooser == 3) {
		// if (inverse == 1) {
		// addSequential(new MoveChassis(12, 0.6));
		// addSequential(new TurnChassis(15, 0.6));
		// addSequential(new MoveChassis(87, 0.6));
		// addSequential(new TurnChassis(-15, 0.6));
		// addSequential(new MoveChassis(85.44, 0.6));
		// //addSequential(new ); Need elevator
		// }
		// else {
		// addSequential(new MoveChassis(12, 0.6));
		// addSequential(new TurnChassis(-15, 0.6));
		// addSequential(new MoveChassis(87, 0.6));
		// addSequential(new TurnChassis(15, 0.6));
		// addSequential(new MoveChassis(85.44, 0.6));
		// //addSequential(new ); Need elevator
		// }
		// }
		//

		// Add Commands here:
		// e.g. addSequential(new Command1());
		// addSequential(new Command2());
		// these will run in order.

		// To run multiple commands at the same time,
		// use addParallel()
		// e.g. addParallel(new Command1());
		// addSequential(new Command2());
		// Command1 and Command2 will run in parallel.

		// A command group will require all of the subsystems that each member
		// would require.
		// e.g. if Command1 requires chassis, and Command2 requires arm,
		// a CommandGroup containing them would require both the chassis and the
		// arm.
	}
}
