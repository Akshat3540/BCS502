//Develp a program for congestion control using a leaky bucket algorithm.
import java.util.*;

public class Lab07_LeakyBucket {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter bucket size, operation rate, and number of seconds: ");
        int cap = sc.nextInt(), process = sc.nextInt(), nsec = sc.nextInt();
        int[] packets = new int[nsec];

        for (int i = 0; i < nsec; i++) {
            System.out.print("Packet at second " + (i + 1) + ": ");
            packets[i] = sc.nextInt();
        }

        System.out.println("\nSec | Recv | Sent | Left | Dropped\n----------------------------");
        int count = 0;
        for (int i = 0; i < nsec; i++) {
            int recv = packets[i], drop = Math.max(0, count + recv - cap);
            count = Math.min(count + recv, cap);
            int sent = Math.min(count, process);
            count -= sent;
            System.out.printf("%3d | %4d | %4d | %4d | %7d%n", i + 1, recv, sent, count, drop);
        }
        sc.close();
    }
}
