// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package bhs.devilbotz.subsystems;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import bhs.devilbotz.Constants;
import bhs.devilbotz.utils.BotType;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Creates a new DriveTrain.
 *
 * @author Sowndy J, Alex C, Parker M, Krish S
 * @since 1.0.0
 */
public class DriveTrain extends SubsystemBase {

    // Define talons


    private static WPI_TalonSRX leftMaster;
    private static WPI_TalonSRX rightMaster;
    private static WPI_TalonSRX leftFollower;
    private static WPI_TalonSRX rightFollower;

    //navx
    private static final AHRS navx = new AHRS(SPI.Port.kMXP);

    //Follower - Master Talon is "given" the instructions and the follower talons controlling the other motors essentially follow the master talons


    //Define differential drive
    private DifferentialDrive differentialDrive;

    public DriveTrain() {
        if (UniqueID.getMacAddress().equals(Constants.practiceBotMAC)) {
            setupBot(BotType.PRACTICE);
        } else if (UniqueID.getMacAddress().equals(Constants.competitionBotMAC)) {
            setupBot(BotType.COMPETITION);
        } else {
            setupBot(BotType.UNKNOWN);
        }
        SmartDashboard.putData("navx", navx);
    }

    private void setupBot(BotType botType) {
        switch (botType) {
            case PRACTICE:
                // Define talons
                leftMaster = new WPI_TalonSRX(3);
                rightMaster = new WPI_TalonSRX(1);
                leftFollower = new WPI_TalonSRX(4);
                rightFollower = new WPI_TalonSRX(2);
                //Define differential drive
                differentialDrive = new DifferentialDrive(rightMaster, leftMaster);

                rightFollower.setInverted(InvertType.FollowMaster);
                rightMaster.setInverted(false);
                leftFollower.setInverted(InvertType.FollowMaster);
                leftMaster.setInverted(true);

                rightFollower.follow(rightMaster);
                leftFollower.follow(leftMaster);
                break;
            case COMPETITION:
                // Define talons
                leftMaster = new WPI_TalonSRX(3);
                rightMaster = new WPI_TalonSRX(1);
                leftFollower = new WPI_TalonSRX(4);
                rightFollower = new WPI_TalonSRX(2);
                //Define differential drive
                differentialDrive = new DifferentialDrive(rightMaster, leftMaster);

                rightFollower.setInverted(InvertType.FollowMaster);
                rightMaster.setInverted(false);
                leftFollower.setInverted(InvertType.FollowMaster);
                leftMaster.setInverted(true);

                rightFollower.follow(rightMaster);
                leftFollower.follow(leftMaster);
                break;
            case UNKNOWN:
                // Define talons
                leftMaster = new WPI_TalonSRX(3);
                rightMaster = new WPI_TalonSRX(1);
                leftFollower = new WPI_TalonSRX(4);
                rightFollower = new WPI_TalonSRX(2);
                //Define differential drive
                differentialDrive = new DifferentialDrive(rightMaster, leftMaster);

                rightFollower.setInverted(InvertType.FollowMaster);
                rightMaster.setInverted(false);
                leftFollower.setInverted(InvertType.FollowMaster);
                leftMaster.setInverted(true);

                rightFollower.follow(rightMaster);
                leftFollower.follow(leftMaster);
                break;
        }
    }


    @Override
    public void periodic() {
        // This method will be called once per scheduler run

    }


    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run during simulation

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

    public void arcadeDrive(double speed, double rotation) {
        differentialDrive.arcadeDrive(speed, rotation);
    }

    public double getGyroAngleY() {
        return navx.getRoll();
    }
}




