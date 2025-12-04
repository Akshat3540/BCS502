import java.io.*;
import java.net.*;
import java.util.*;
import java.nio.file.*;

class Client {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the server address: ");
        try (Socket s = new Socket(sc.nextLine(), 5000);
                DataInputStream in = new DataInputStream(s.getInputStream());
                DataOutputStream out = new DataOutputStream(s.getOutputStream())) {

            System.out.print("Send 'start' to begin... ");
            if (!sc.nextLine().equals("start"))
                return;
            out.writeUTF("start");

            String meta = in.readUTF();
            if (meta.startsWith("File") || meta.startsWith("Invalid")) {
                System.out.println("Server: " + meta);
                return;
            }

            String[] parts = meta.split("\\|");
            System.out.print("Recieving file: " + parts[0] + "\nSaving as: client_" + parts[0] + "\nFile Size: "
                    + parts[1] + "\nRecieving file...\n");

            Files.copy(in, Paths.get("client_" + parts[0]), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File transfer completed.");
        }
        sc.close();
    }
}