import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.*;

public class Server {
    public static void main(String[] args) throws Exception {
        System.out.print("Enter the file name: ");
        File f = new File(new Scanner(System.in).nextLine());

        System.out.println("Server started. Waiting for client...");
        try (ServerSocket ss = new ServerSocket(5000);
                Socket s = ss.accept();
                DataInputStream in = new DataInputStream(s.getInputStream());
                DataOutputStream out = new DataOutputStream(s.getOutputStream())) {

            System.out.println("Connected to client.");
            if (!"start".equals(in.readUTF())) {
                out.writeUTF("Request not valid");
                return;
            }
            System.out.println("Recieved 'start' request.");

            if (!f.exists()) {
                out.writeUTF("File not found");
                return;
            }

            out.writeUTF(f.getName());
            out.writeLong(f.length());
            System.out.print("Sending file: " + f.getName() + "\nFile Size: " + f.length() + "\n");

            Files.copy(f.toPath(), out);
            System.out.println("File Sent successfully.");
        }
    }
}