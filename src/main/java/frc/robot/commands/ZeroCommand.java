package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.elevator.ElevatorSubsystem;
import frc.robot.subsystems.endeffector.EndEffectorSubsystem;
import frc.robot.subsystems.intake.IntakeSubsystem;

public class ZeroCommand extends SequentialCommandGroup {
    public ZeroCommand(ElevatorSubsystem elevatorSubsystem, IntakeSubsystem intakeSubsystem, EndEffectorSubsystem endEffectorSubsystem) {
        addRequirements(elevatorSubsystem, intakeSubsystem, endEffectorSubsystem);
        addCommands(
                Commands.parallel(
                        Commands.runOnce(() -> {
                            endEffectorSubsystem.setWantedState(EndEffectorSubsystem.WantedState.IDLE);
                        }),
                        Commands.runOnce(() -> {
                            intakeSubsystem.setWantedState(IntakeSubsystem.WantedState.GROUNDZERO);
                        }),
                        Commands.runOnce(() -> {
                            elevatorSubsystem.setElevatorState(ElevatorSubsystem.WantedState.ZERO);
                        })
                )
        );
    }

    @Override
    public InterruptionBehavior getInterruptionBehavior() {
        return InterruptionBehavior.kCancelIncoming;
    }
}
