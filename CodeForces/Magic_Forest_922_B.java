
/**
 * Date : 8 Feb, 2018
 * Link : http://codeforces.com/contest/922/problem/B
 *
 * @author Prasad-Chaudhari
 * @email prasadc8897@gmail.com
 */
import java.util.Scanner;

public class Magic_Forest_922_B {
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int count = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j++) {
                int c = i ^ j;
                if (c <= i || c <= j || c > n) {
                    continue;
                }
                if (i + j > c) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}
