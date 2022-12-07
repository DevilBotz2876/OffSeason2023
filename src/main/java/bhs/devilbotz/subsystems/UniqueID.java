package bhs.devilbotz.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UniqueID extends SubsystemBase {
    @Override
    public void periodic() {
        // This method will be called once per scheduler run


    }


    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run during simulation


    }

    public static String getMacAddress() {
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
