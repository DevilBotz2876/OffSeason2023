/*-------------------------------------------------------------------------------*/
/* Copyright (c) 2022-2023 BHS Devilbotz. All Rights Reserved.                   */
/* Open Source Software - may be modified, commercialized, distributed,          */
/* sub-licensed and used for private use under the terms of the License.md       */
/* file in the root of the source code tree.                                     */
/*                                                                               */
/* You MUST include the original copyright and license files in any and all      */
/* revised/modified code. You may NOT remove this header under any circumstance  */
/* unless explicitly noted                                                       */
/*-------------------------------------------------------------------------------*/

package bhs.devilbotz.commands;

import bhs.devilbotz.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ArcadeDrive extends CommandBase {
    private final DriveTrain drive;
    private final Joystick stick;

    public ArcadeDrive(DriveTrain drive, Joystick stick) {
        this.drive = drive;
        this.stick = stick;
        addRequirements(this.drive);
    }

    @Override
    public void execute() {
        double speed = stick.getY();
        double rotation = stick.getX();
        drive.arcadeDrive(-speed, -rotation);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}
