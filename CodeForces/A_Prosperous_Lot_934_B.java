
/**
 * Date: 14 Feb, 2018
 * Link : http://codeforces.com/problemset/problem/934/B
 *
 * @author Prasad-Chaudhari
 * @email prasadc8897@gmail.com
 */
import java.util.Scanner;

public class A_Prosperous_Lot_934_B {

    public static void main(String[] args) {
        // TODO code application logic here
        Scanner in = new Scanner(System.in);
        int k = in.nextInt();
        if (k > 36) {
            System.out.println("-1");
        } else {
            StringBuilder sb = new StringBuilder("");
            int mul = k / 2;
            while (mul-- > 0) {
                sb.append("8");
            }
            if (k % 2 == 1) {
                sb.append("4");
            }
            System.out.println(sb.toString());
        }
    }
}
