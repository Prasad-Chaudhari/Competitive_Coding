
/**
 * Date: 17 Feb, 2018
 * Link : http://codeforces.com/contest/939/problem/B
 *
 * @author Prasad-Chaudhari
 * @email prasadc8897@gmail.com
 */
import java.util.Scanner;

public class Hamster_Farm_939_B {

    public static void main(String[] args) {
        // TODO code application logic here
        Scanner in = new Scanner(System.in);
        long n = in.nextLong();
        int k = in.nextInt();
        long a[] = new long[k];
        for (int i = 0; i < k; i++) {
            a[i] = in.nextLong();
        }
        long min = Long.MAX_VALUE;
        int box = -1;
        for (int i = 0; i < k; i++) {
            long mod = n % a[i];
            if (min > mod) {
                min = mod;
                box = i;
            }
        }
        System.out.println((box + 1) + " " + n / a[box]);
    }

}
