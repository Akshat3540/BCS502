import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class Lab6_RSA {
    public static void main(String[] args) {
        Random r = new Random();
        BigInteger p = BigInteger.probablePrime(512, r), q = BigInteger.probablePrime(512, r);
        BigInteger N = p.multiply(q), phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        BigInteger e = BigInteger.probablePrime(256, r);
        while (e.compareTo(phi) >= 0 || !phi.gcd(e).equals(BigInteger.ONE)) e = e.add(BigInteger.ONE);
        BigInteger d = e.modInverse(phi);

        System.out.println("Prime (p): " + p);
        System.out.println("Prime (q): " + q);
        System.out.println("Public key (e): " + e);
        System.out.println("Private key (d): " + d);

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter plaintext: ");
        String msg = sc.nextLine();
        sc.close();

        BigInteger message = new BigInteger(msg.getBytes());
        BigInteger encrypted = message.modPow(e, N), decrypted = encrypted.modPow(d, N);

        System.out.println("\nEncrypting: " + msg);

        System.out.print("Plaintext in bytes: ");
        for (byte b : msg.getBytes()) System.out.print((b & 0xFF) + "-");
        System.out.println();

        System.out.print("Encrypted Bytes: ");
        for (byte b : encrypted.toByteArray()) System.out.print((b & 0xFF) + "-");
        System.out.println();

        System.out.print("Decrypted Bytes: ");
        for (byte b : decrypted.toByteArray()) System.out.print((b & 0xFF) + "-");
        System.out.println();

        System.out.println("Decrypted String: " + new String(decrypted.toByteArray()));
    }
}
