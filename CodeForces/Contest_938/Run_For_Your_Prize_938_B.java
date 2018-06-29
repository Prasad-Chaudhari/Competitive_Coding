
/**
 * Date: 16 Feb, 2018
 * Link : http://codeforces.com/problemset/problem/938/B
 *
 * @author Prasad-Chaudhari
 * @email prasadc8897@gmail.com
 */
import java.util.Scanner;

public class Run_For_Your_Prize_938_B {

    public static void main(String[] args) {
        // TODO code application logic here
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int a[] = new int[1000000];
        for (int i = 0; i < n; i++) {
            a[in.nextInt()]++;
        }
        int objects = 0;
        for (int i = 1; i < 1000000; i++) {
            if (a[i] == 1) {
                objects++;
                if (objects >= n) {
                    System.out.println(i - 1);
                    break;
                }
            }
            if (a[1000000 - i] == 1) {
                objects++;
                if (objects >= n) {
                    System.out.println(i);
                    break;
                }
            }

        }
    }
}
