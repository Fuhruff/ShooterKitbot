// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.elevator;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import edu.wpi.first.wpilibj.simulation.ElevatorSim;
import frc.robot.subsystems.elevator.*;

/** Add your docs here. */
public class ElevatorIOSim implements ElevatorIO {

    private final double kP = 0.004;
    private final double kI = 0.006;
    private final double kD = 0.0;

    private final PIDController pid = new PIDController(kP, kI, kD);
    private double setpoint = 0.0;
    private double manualVoltage = 0.0;

    public ElevatorIOSim() {
        elevatorSim = new ElevatorSim(
            LinearSystemId.createElevatorSystem(DCMotor.getNEO(1), 0.0, 0.0, ElevatorConstants.gearing));
}
