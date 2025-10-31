import java.util.*;

public class Lab2_SWP {
    public static void main(String[] args) throws Exception {
        final int W = 4, N = 10;
        Queue<Integer> q = new LinkedList<>();
        int next = 0;

        while (next < N || !q.isEmpty()) {
            while (q.size() < W && next < N) {
                System.out.println("Sent packet: " + next);
                q.add(next++);
            }
            System.out.println("Ack received for packet: " + q.poll());
            Thread.sleep(500);
        }
        System.out.println("All packets sent and acknowledged.");
    }
}
