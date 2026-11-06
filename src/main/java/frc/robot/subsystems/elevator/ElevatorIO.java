// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.elevator;

import org.littletonrobotics.junction.AutoLog;

/** Add your docs here. */
public interface ElevatorIO {

    @AutoLog
    public class ElevatorInputs {

        public double position = 0.0;
        public double velocity = 0.0;
        public double voltage = 0.0;
        public double current = 0.0;
        public double setpoint = 0.0;

    }

    public default void updateInputs(ElevatorInputs inputs) {}

    public default void setVelocity(double velocity) {}

    public default void setVoltage(double voltage) {}

    public default void setPosition(double height) {}
}
