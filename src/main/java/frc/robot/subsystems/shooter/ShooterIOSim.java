// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.shooter;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.wpilibj.simulation.FlywheelSim;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.Subsystem;


public class ShooterIOSim implements ShooterIO {
  
  private final double kP = 0.004;
  private final double kI = 0.006;
  private final double kD = 0.0;

  private final PIDController pid = new PIDController(kP, kI, kD);
  private final FlywheelSim shooter;
  private double setpoint = 0.0;
  private double manualVoltage = 0.0;

  public ShooterIOSim() {

    DCMotor motor = DCMotor.getNEO(1);
    shooter = new FlywheelSim(
        LinearSystemId.createFlywheelSystem(motor, 0.003, 1.0), motor);
  }

  @Override
  public void updateInputs(ShooterIOInputs inputs) {
    double velocity = shooter.getAngularVelocityRPM();
    double voltage = manualVoltage + pid.calculate(velocity, setpoint);
    voltage = MathUtil.clamp(voltage, -12.0, 12.0);
    shooter.setInputVoltage(voltage);
    shooter.update(0.02);

    inputs.velocity = shooter.getAngularVelocityRPM();
    inputs.setpointRPM = setpoint;
    inputs.voltage = voltage;
  }

  @Override
  public void setVelocity(double velocityRPM) {
    manualVoltage = 0.0;
    setpoint = velocityRPM;
    pid.setPID(kP, kI, kD);
  }

  @Override
  public void setVoltage(double volts) {

    pid.setPID(0.0, 0.0, 0.0);
    pid.reset();
    setpoint = 0.0;
    manualVoltage = MathUtil.clamp(volts, -12.0, 12.0);
    shooter.setInputVoltage(manualVoltage);
  }

  @Override
  public void stop() {
    setVoltage(0.0);
  }


  @Override
  public Command setVelocityCommand(double velocityRPM, Subsystem subsystem) {
    return Commands.runOnce(() -> setVelocity(velocityRPM), subsystem);
  }

  @Override
  public Command setVoltageCommand(double volts, Subsystem subsystem) {
    return Commands.runOnce(() -> setVoltage(volts), subsystem);
  }

  @Override
  public Command stopCommand(Subsystem subsystem) {
    return Commands.runOnce(() -> stop(), subsystem);
  }

}
