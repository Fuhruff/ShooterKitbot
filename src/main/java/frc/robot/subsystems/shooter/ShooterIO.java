// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.shooter;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;

public interface ShooterIO {
  class ShooterIOInputs {
    public double velocity = 0.0;
    public double setpointRPM = 0.0;
    public double voltage = 0.0;
  }

  default void updateInputs(ShooterIOInputs inputs) {}
  default void setVelocity(double velocityRPM) {}
  default void setVoltage(double volts) {}
  default void stop() {}

  Command setVelocityCommand(double velocityRPM, Subsystem subsystem);
  Command setVoltageCommand(double volts, Subsystem subsystem);
  Command stopCommand(Subsystem subsystem);
}
