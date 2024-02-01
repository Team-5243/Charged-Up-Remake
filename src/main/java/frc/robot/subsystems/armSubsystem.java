// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.armCommand;

public class armSubsystem extends SubsystemBase {
  /** Creates a new armSubsystem. */
  public CANSparkMax armLift;
  public CANSparkMax armExtend;
  public armSubsystem() {
    armLift = new CANSparkMax(Constants.ARMLIFT, MotorType.kBrushless);
    armExtend = new CANSparkMax(Constants.ARMEXTEND, MotorType.kBrushless);
    
  }

  public boolean armCondition() {
    // Query some boolean state, such as a digital sensor.
    return false;
  }

  
  public void armLiftMethod(){
    armLift.set(0.5*Constants.secondStick.getY());
  }
  
  public void armExtendMethod(){
    if(Constants.secondStick.getTrigger()){
      armExtend.set(0.1);
    }else if(Constants.secondStick.getRawButton(2)){
      armExtend.set(-0.1);  
    } else{
      armExtend.set(0);
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    setDefaultCommand(new armCommand(this));
  }
}
