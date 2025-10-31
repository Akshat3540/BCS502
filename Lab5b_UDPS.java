import java.net.*;
public class Lab5b_UDPS {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(6788);
        byte[] buf = new byte[1000];
        System.out.println("UDP Server running on port 6788...");
        while (true) {
            DatagramPacket pkt = new DatagramPacket(buf, buf.length);
            socket.receive(pkt);
            String msg = new String(pkt.getData(), 0, pkt.getLength());
            System.out.println("Received: " + msg);
            String ack = msg;
            DatagramPacket reply = new DatagramPacket(ack.getBytes(), ack.length(), pkt.getAddress(), pkt.getPort());
            socket.send(reply);
            System.out.println("Acknowledgement sent for: " + msg);
        }
    }
}

