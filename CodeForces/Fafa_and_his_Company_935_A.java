
/**
 * Date: 19 Feb, 2018
 * Link: http://codeforces.com/problemset/problem/935/A
 *
 * @author Prasad-Chaudhari
 * @email prasadc8897@gmail.com
 */
import java.util.Scanner;

public class Fafa_and_his_Company_935_A {

    public static void main(String[] args) {
        // TODO code application logic here
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int count = 0;
        for (int i = 1; i < n; i++) {
            if (n % i == 0) {
                count++;
            }
        }
        System.out.println(count);
    }
}
