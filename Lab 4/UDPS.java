import java.net.*;

public class UDPS {
    public static void main(String[] args) {
        try (DatagramSocket skt = new DatagramSocket(6788)) {
            byte[] buffer = new byte[1000];
            System.out.println("UDP Server running on port 6788...");

            while (true) {
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                skt.receive(request);

                String received = new String(request.getData(), 0, request.getLength());
                System.out.println("Received: " + received);

                String response = received + " server processed";
                byte[] sendMsg = response.getBytes();

                DatagramPacket reply = new DatagramPacket(
                        sendMsg, sendMsg.length, request.getAddress(), request.getPort());
                skt.send(reply);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
