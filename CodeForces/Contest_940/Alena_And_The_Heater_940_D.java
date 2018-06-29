
/**
 * Date: 24 Feb, 2018
 * Link : http://codeforces.com/contest/940/problem/D
 *
 * @author Prasad-Chaudhari
 * @email prasadc8897@gmail.com
 */
import java.util.Scanner;

public class Alena_And_The_Heater_940_D {

    public static void main(String[] args) {
        // TODO code application logic here
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int a[] = new int[n + 1];
        int b[] = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = in.nextInt();
        }
        char c[] = in.next().toCharArray();
        for (int i = 1; i <= n; i++) {
            b[i] = c[i - 1] - '0';
        }
        int l = -1000000000;
        int r = 1000000000;
        for (int i = n; i >= 5; i--) {
            if (b[i] == 0) {
                if (b[i - 1] == 1) {
                    if (bcheck(b, i, 1)) {
                        r = Math.min(r, mina(a, i) - 1);
                    }
                }
            } else {
                if (b[i - 1] == 0) {
                    if (bcheck(b, i, 0)) {
                        l = Math.max(l, maxa(a, i) + 1);
                    }
                }
            }
        }
        System.out.println(l + " " + r);
    }

    private static boolean bcheck(int[] b, int i, int i0) {
        return b[i - 1] == b[i - 2] && b[i - 2] == b[i - 3] && b[i - 3] == b[i - 4] && b[i - 4] == i0;
    }

    private static int maxa(int[] a, int i) {
        return Math.max(a[i - 4], Math.max(a[i - 3], Math.max(a[i - 2], Math.max(a[i - 1], a[i]))));
    }

    private static int mina(int[] a, int i) {
        return Math.min(a[i - 4], Math.min(a[i - 3], Math.min(a[i - 2], Math.min(a[i - 1], a[i]))));
    }
}
