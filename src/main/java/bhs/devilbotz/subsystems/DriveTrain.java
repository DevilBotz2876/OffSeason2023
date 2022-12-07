// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package bhs.devilbotz.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.InvertType;

/**
 * Creates a new DriveTrain.
 *
 * @author Sowndy J, Alex C, Parker M, Krish S
 * @since 1.0.0
 */
public class DriveTrain extends SubsystemBase {

    // Define talons


    private static final WPI_TalonSRX leftMaster = new WPI_TalonSRX(3);
    private static final WPI_TalonSRX rightMaster = new WPI_TalonSRX(1);
    private static final WPI_TalonSRX leftFollower = new WPI_TalonSRX(4);
    private static final WPI_TalonSRX rightFollower = new WPI_TalonSRX(2);

    //Follower - Master Talon is "given" the instructions and the follower talons controlling the other motors essentially follow the master talons


    //Define differential drive
    private final DifferentialDrive differentialDrive = new DifferentialDrive(rightMaster, leftMaster);



    public DriveTrain() {
        setupTalons();
    }


    @Override
    public void periodic() {
        // This method will be called once per scheduler run


    }


    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run during simulation


    }

    private void setupTalons() {
        rightFollower.setInverted(InvertType.FollowMaster);
        rightMaster.setInverted(false);
        leftFollower.setInverted(InvertType.FollowMaster);
        leftMaster.setInverted(true);

        rightFollower.follow(rightMaster);
        leftFollower.follow(leftMaster);
    }

    /**
     * Tank drive method
     *
     * @param leftSpeed The speed of the left side of the robot
     * @param rightSpeed The speed of the right side of the robot
     * @since 1.0.0
     */
    public void tankDrive(double leftSpeed, double rightSpeed) {
        differentialDrive.tankDrive(leftSpeed, rightSpeed);
    }

    public WPI_TalonSRX getLeftFollower() {
        return leftFollower;
    }

    public WPI_TalonSRX getRightFollower() {
        return rightFollower;
    }
}




