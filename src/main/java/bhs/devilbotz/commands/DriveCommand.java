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
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj2.command.CommandBase;

import java.util.function.DoubleSupplier;

public class DriveCommand extends CommandBase {
    private final DriveTrain drive;
    private final DoubleSupplier speed;
    private final DoubleSupplier rotation;

    private final SlewRateLimiter slew = new SlewRateLimiter(2);

    public DriveCommand(DriveTrain drive, DoubleSupplier speed, DoubleSupplier rotation) {
        this.drive = drive;
        this.speed = speed;
        this.rotation = rotation;
        addRequirements(this.drive);
    }

    @Override
    public void execute() {
         double s = speed.getAsDouble();
         double r = rotation.getAsDouble();



        drive.arcadeDrive(s, slew.calculate(r));

    }

    @Override
    public boolean isFinished() {
        return false;
    }

}
