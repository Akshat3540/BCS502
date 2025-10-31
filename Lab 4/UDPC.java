import java.net.*;
import java.util.*;
public class UDPC {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter message: ");
        String msg = sc.nextLine();
        InetAddress host = InetAddress.getByName("127.0.0.1");
        DatagramPacket req = new DatagramPacket(msg.getBytes(), msg.length(), host, 6788);
        socket.send(req);
        System.out.println("Message sent: " + msg);
        byte[] buf = new byte[1000];
        DatagramPacket rep = new DatagramPacket(buf, buf.length);
        socket.receive(rep);
        String ack = new String(rep.getData(), 0, rep.getLength());
        System.out.println("Acknowledgement Recieved for: " + ack);
    }
}

