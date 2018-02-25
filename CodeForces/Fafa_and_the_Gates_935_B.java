
/**
 * Date: 19 Feb, 2018
 * Link : http://codeforces.com/problemset/problem/935/B
 *
 * @author Prasad-Chaudhari
 * @email prasadc8897@gmail.com
 */
import java.util.Scanner;

public class Fafa_and_the_Gates_935_B {

    public static void main(String[] args) {
        // TODO code application logic here
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        char[] c = in.next().toCharArray();
        int x = c[0] == 'R' ? 1 : 0;
        int y = c[0] == 'U' ? 1 : 0;
        int count = 0;
        boolean[] b = new boolean[n];
        for (int i = 1; i < n - 1; i++) {
            if (c[i] == 'U') {
                y++;
            }
            if (c[i] == 'R') {
                x++;
            }
            if (x == y) {
                if (c[i] == c[i + 1]) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}
