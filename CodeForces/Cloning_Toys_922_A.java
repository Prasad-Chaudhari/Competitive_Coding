
/**
 * Date : 8 Feb, 2018
 * Link : http://codeforces.com/contest/922/problem/A
 *
 * @author Prasad-Chaudhari
 * @email prasadc8897@gmail.com
 */
import java.util.Scanner;

public class Cloning_Toys_922_A {
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner in = new Scanner(System.in);
        int x = in.nextInt();
        int y = in.nextInt();
        if (y == 0) {
            System.out.println("No");
            System.exit(0);
        }
        if (y == 1 && x != 0) {
            System.out.println("No");
            System.exit(0);
        }
        if (x < y - 1) {
            System.out.println("No");
            System.exit(0);
        }
        x = x - (y - 1);
        if (x % 2 == 0) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}
