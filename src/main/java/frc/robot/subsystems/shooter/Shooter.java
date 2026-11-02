package frc.robot.subsystems.shooter;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
  private final ShooterIO io;
  private final ShooterIO.ShooterIOInputs inputs = new ShooterIO.ShooterIOInputs();

  public Shooter(ShooterIO io) {
    this.io = io;
    SmartDashboard.putNumber("Shooter/TargetRPM", 3000.0);
  }

  @Override
  public void periodic() {
    io.updateInputs(inputs);
    SmartDashboard.putNumber("Shooter/SetpointRPM", inputs.setpointRPM);
    SmartDashboard.putNumber("Shooter/VelocityRPM", inputs.velocity);
    SmartDashboard.putNumber("Shooter/AppliedVolts", inputs.voltage);
  }

  public Command setVelocity(double velocityRPM) {
    return io.setVelocityCommand(velocityRPM, this);
  }

  public Command setVoltage(double volts) {
    return io.setVoltageCommand(volts, this);
  }

  public Command stop() {
    return io.stopCommand(this);
  }

  // Read the target while running, and stop when the command ends.
  public Command runShooter() {
    return this.runEnd(
        () -> io.setVelocity(SmartDashboard.getNumber("Shooter/TargetRPM", 3000.0)),
        () -> io.stop());
  }

  public double getVelocity() {
    return inputs.velocity;
  }
}


