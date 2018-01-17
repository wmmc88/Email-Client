/**
 * Created by Melvin Wang on 2016-04-27.
 */

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class NetIO {
    public static String myUserName() {
        return System.getProperty("user.name");
    }

    public static String myIPAddress() {
        String ipAddress = "";
        try {
            InetAddress me = InetAddress.getLocalHost();
            ipAddress = me.getHostAddress();
        } catch (IOException e) {
            System.out.println(e);
        }
        return ipAddress;
    }

    public static int sendRequest(String message, String destinationIPAddress) {
        int errorCode = Globals.NET_SEND_ERROR;
        int attempts = 0;
        {
            try {
                Socket me = new Socket();
                me.connect(new InetSocketAddress(destinationIPAddress, 5000), 10000);
                me.setSoTimeout(10000);

                DataOutputStream output = new DataOutputStream(me.getOutputStream());
                output.writeUTF(message);

                DataInputStream input = new DataInputStream(me.getInputStream());
                String confirmation = input.readUTF();

                if (Utils.isANumber(confirmation))
                    errorCode = Integer.parseInt(confirmation);

                me.close();
            } catch (IOException e) {
                System.out.println(e);
            }
            attempts++;
        }
        while (errorCode != Globals.NET_OK && attempts < Globals.SENDING_ATTEMPTS_LIMIT) ;
        return errorCode;
    }

    public static String receiveRequest() {
        String message = "";
        try {
            ServerSocket server = new ServerSocket(5000, 100);
            Socket me = server.accept();
            me.setSoTimeout(10000);

            DataInputStream input = new DataInputStream(me.getInputStream());
            message = input.readUTF();

            DataOutputStream output = new DataOutputStream(me.getOutputStream());
            output.writeUTF("0");

            me.close();
            server.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        return message;
    }

}
