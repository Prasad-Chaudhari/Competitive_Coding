
/**
 * Date: 17 Feb, 2018
 * Link : http://codeforces.com/contest/939/problem/A
 *
 * @author Prasad-Chaudhari
 * @email prasadc8897@gmail.com
 */
import java.util.Scanner;

public class Love_Triangle_939_A {

    public static void main(String[] args) {
        // TODO code application logic here
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int a[] = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = in.nextInt();
        }
        for (int i = 1; i <= n; i++) {
            int p = a[i];
            int q = a[p];
            int r = a[q];
            if (a[r] == a[i]) {
                System.out.println("YES");
                System.exit(0);
            }
        }
        System.out.println("NO");
    }
}
