import java.io.*;  
import java.net.*;  
import java.util.*;  

class Lab4b_TCPClient {
    public static void main(String[] a) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Server IP: ");
        try (Socket s = new Socket(sc.nextLine(), 5000); DataInputStream in = new DataInputStream(s.getInputStream()); DataOutputStream out = new DataOutputStream(s.getOutputStream())) {

            System.out.print("Type 'start' to request file: ");
            if (!new Scanner(System.in).nextLine().equals("start")) return;
            out.writeUTF("start");

            String fname = in.readUTF();
            if (fname.contains("not") || fname.contains("Invalid")) { 
                System.out.println("Server: " + fname); return; 
            }

            long size = Long.parseLong(in.readUTF());
            System.out.println("Receiving " + fname + " (" + size / 1024 + " KB)");

            try (FileOutputStream f = new FileOutputStream("client_" + fname)) {
                byte[] buf = new byte[1024];
                for (int n; (n = in.read(buf)) != -1 && (size -= n) > 0;) f.write(buf, 0, n);
            }
            System.out.println("File received.");
        }
    }
}
