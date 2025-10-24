import java.net.*;
import java.util.Scanner;

public class UDPC {
    public static void main(String[] args) throws Exception{
        DatagramSocket skt = null;
        try {
            skt = new DatagramSocket();
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the message:");
            String msg = sc.nextLine();
            byte[] b = msg.getBytes();
            InetAddress host = InetAddress.getByName("127.0.0.1");
            int serverPort = 6788;
            DatagramPacket request = new DatagramPacket(b, b.length, host, serverPort);
            skt.send(request);
            byte[] buffer = new byte[1000];
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            skt.receive(reply);
            String receivedMessage = new String(reply.getData(), 0, reply.getLength());
            System.out.println("Sent: " + msg);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (skt != null) {
                skt.close();
            }
        }
    }
}

