// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.DriveCommand;

public class DriveSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */

  public RelativeEncoder flEncoder;
  public RelativeEncoder frEncoder;
  public RelativeEncoder blEncoder;
  public RelativeEncoder brEncoder;
  

  public CANSparkMax fl;
  public CANSparkMax fr;
  public CANSparkMax bl;
  public CANSparkMax br;
  public MotorControllerGroup left;
  public MotorControllerGroup right;
  public DifferentialDrive diffDrive;

  public DriveSubsystem() {
    fl = new CANSparkMax(Constants.FL, MotorType.kBrushless);
    fr = new CANSparkMax(Constants.FR, MotorType.kBrushless);
    bl = new CANSparkMax(Constants.BL, MotorType.kBrushless);
    br = new CANSparkMax(Constants.BR, MotorType.kBrushless);

    flEncoder = fl.getEncoder();
    frEncoder = fr.getEncoder();
    blEncoder = bl.getEncoder();
    brEncoder = br.getEncoder();

    

    // fl.setNeutralMode(IdleMode.kBrake);
    left = new MotorControllerGroup(fl,bl);
    right = new MotorControllerGroup(fr,br);
    diffDrive = new DifferentialDrive(left, right);
  }

  /**
   * Example command factory method.
   *
   * @return a command
   */
  public CommandBase exampleMethodCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(
        () -> {
          /* one-time action goes here */
        });
  }


  public void arcadeDrive(){
    diffDrive.arcadeDrive(Constants.mainStick.getX(), Constants.mainStick.getY());
  } 

  public void driveForward() {
    diffDrive.arcadeDrive(-.102, -0.5);
  }

  public void stopDrive(){
    diffDrive.arcadeDrive(0, 0);
  }

  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean exampleCondition() {
    // Query some boolean state, such as a digital sensor.
    return false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
      setDefaultCommand(new DriveCommand(this));
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
