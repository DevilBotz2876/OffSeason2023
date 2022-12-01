package bhs.devilbotz.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import java.lang.reflect.Array;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Arrays;

public class GetUniqueID extends CommandBase {
    /**
     * Creates a new GetUniqueID.
     */
    public GetUniqueID() {
        // Use addRequirements() here to declare subsystem dependencies.
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        getMacAddress();
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return true;
    }

    private String getMacAddress() {
        try {
            NetworkInterface network = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());
            byte[] mac = network.getHardwareAddress();

            StringBuilder macString = new StringBuilder();
            for(byte m: mac) {
                macString.append(String.format("%02X", m).replace("-", ""));
            }
            return macString.toString();
            // TODO: Implement checking for the practice bot
        } catch (SocketException | UnknownHostException e) {
            throw new RuntimeException(e);
        }

    }
}

