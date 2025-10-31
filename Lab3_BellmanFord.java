import java.util.*;

public class Lab3_BellmanFord {
    static final int INF = 999;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of vertices: ");
        int n = sc.nextInt(), g[][] = new int[n + 1][n + 1], d[] = new int[n + 1];

        System.out.println("Enter weighted adjacency matrix:");
        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= n; j++) {
                g[i][j] = sc.nextInt();
                if (i == j) g[i][j] = 0;
                else if (g[i][j] == 0) g[i][j] = INF;
            }

        System.out.print("Enter source vertex: ");
        int s = sc.nextInt();
        Arrays.fill(d, INF);
        d[s] = 0;

        for (int k = 1; k < n; k++)
            for (int i = 1; i <= n; i++)
                for (int j = 1; j <= n; j++)
                    if (g[i][j] != INF && d[i] + g[i][j] < d[j])
                        d[j] = d[i] + g[i][j];

        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= n; j++)
                if (g[i][j] != INF && d[i] + g[i][j] < d[j]) {
                    System.out.println("Graph contains a negative edge cycle");
                    sc.close();
                    return;
                }

        for (int i = 1; i <= n; i++)
            System.out.println("Distance from " + s + " to " + i + " is " + d[i]);
        sc.close();
    }
}
