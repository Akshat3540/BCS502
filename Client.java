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

            String fname = in.readUTF();
            if (fname.contains("not")) {
                System.out.println(fname);
                return;
            }

            long size = in.readLong();
            System.out.print("Recieving file: " + fname + "\nSaving as: client_" + fname + "\nFile Size: " + size
                    + "\nRecieving file...\n");

            Files.copy(in, Paths.get("client_" + fname));
            System.out.println("File transfer completed.");
        }
        sc.close();
    }
}