import java.io.*;
import java.net.*;

public class TCPServer {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter file name: ");
        File f = new File(br.readLine());

        try (ServerSocket ss = new ServerSocket(5000);
             Socket s = ss.accept();
             DataInputStream in = new DataInputStream(s.getInputStream());
             DataOutputStream out = new DataOutputStream(s.getOutputStream())) {

            System.out.println("Client connected.");
            if (!"start".equals(in.readUTF())) { out.writeUTF("Invalid request"); return; }
            if (!f.exists()) { out.writeUTF("File not found"); return; }

            out.writeUTF(f.getName());
            out.writeUTF(Long.toString(f.length()));
            try (FileInputStream fin = new FileInputStream(f)) {
                byte[] buf = new byte[1024]; int n;
                while ((n = fin.read(buf)) != -1) out.write(buf, 0, n);
            }
            System.out.println("File sent successfully.");
        }
    }
}
