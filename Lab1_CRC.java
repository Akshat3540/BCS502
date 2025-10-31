import java.util.*;

public class Lab1_CRC {
    static int gp[] = {1,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,1};

    static void divide(int[] data, int k) {
        for (int i = 0; i < k; i++)
            if (data[i] == 1)
                for (int j = 0; j < gp.length; j++)
                    data[i + j] ^= gp[j];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int data[] = new int[100];
        System.out.print("Enter length of the data frame: ");
        int n = sc.nextInt();
        System.out.println("Enter the data bits:");
        for (int i = 0; i < n; i++) data[i] = sc.nextInt();

        int len = n + gp.length - 1;
        int temp[] = Arrays.copyOf(data, len);
        divide(temp, n);

        System.out.println("Transmitted data: ");
        for (int i = 0; i < n; i++) System.out.print(data[i]);
        for (int i = n; i < len; i++) System.out.print(temp[i]);
        System.out.println();

        System.out.println("Enter received data:");
        String rcv = sc.next();
        for (int i = 0; i < len; i++) data[i] = rcv.charAt(i) - '0';
        divide(data, n);

        boolean error = false;
        for (int x : data) if (x != 0) { error = true; break; }
        System.out.println(error ? "ERROR in data" : "NO ERROR");
        sc.close();
    }
}
