package bhs.devilbotz.commands;

import bhs.devilbotz.subsystems.DriveTrain;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import org.photonvision.PhotonCamera;

public class AimAtTarget extends CommandBase {
    private final DriveTrain drive;
    // Constants such as camera and target height stored. Change per robot and goal!
    final double CAMERA_HEIGHT_METERS = Units.inchesToMeters(24);
    final double TARGET_HEIGHT_METERS = Units.feetToMeters(5);
    // Angle between horizontal and the camera.
    final double CAMERA_PITCH_RADIANS = Units.degreesToRadians(0);

    // How far from the target we want to be
    final double GOAL_RANGE_METERS = Units.feetToMeters(3);

    // PID constants should be tuned per robot
    final double LINEAR_P = 0.1;
    final double LINEAR_D = 0.0;
    PIDController forwardController = new PIDController(LINEAR_P, 0, LINEAR_D);

    final double ANGULAR_P = 0.025;
    final double ANGULAR_D = 0.2;
    PIDController turnController = new PIDController(ANGULAR_P, 0, ANGULAR_D);

    // Change this to match the name of your camera
    PhotonCamera camera = new PhotonCamera("Logitech_Webcam_C930e");

    public AimAtTarget(DriveTrain drive) {
        this.drive = drive;
        addRequirements(this.drive);
    }

    @Override
    public void execute() {
        double forwardSpeed = 0;
        double rotationSpeed = 0;

        var result = camera.getLatestResult();

        if (result.hasTargets()) {
            // Calculate angular turn power
            System.out.println("bbb");
            // -1.0 required to ensure positive PID controller effort _increases_ yaw
            rotationSpeed = -turnController.calculate(result.getBestTarget().getYaw(), 0);
        } else {
            System.out.println("ccc");

            // If we have no targets, stay still.
            rotationSpeed = 0;
            forwardSpeed = 0;
        }
        drive.arcadeDrive(forwardSpeed, rotationSpeed);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
