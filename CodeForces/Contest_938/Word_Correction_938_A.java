
/**
 * Date: 16 Feb, 2018
 * Link : http://codeforces.com/problemset/problem/938/A
 *
 * @author Prasad-Chaudhari
 * @email prasadc8897@gmail.com
 */
import java.util.Scanner;

public class Word_Correction_938_A {

    public static void main(String[] args) {
        // TODO code application logic here
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        char c[] = in.next().toCharArray();
        StringBuilder sb = new StringBuilder(c[0] + "");
        int i = 0;
        boolean p = c[i] == 'u' || c[i] == 'o' || c[i] == 'i' || c[i] == 'e' || c[i] == 'a' || c[i] == 'y';
        for (i = 1; i < n; i++) {
            if (c[i] == 'u' || c[i] == 'o' || c[i] == 'i' || c[i] == 'e' || c[i] == 'a' || c[i] == 'y') {
                if (!p) {
                    sb.append(c[i]);
                }
            } else {
                sb.append(c[i]);
            }

            p = c[i] == 'u' || c[i] == 'o' || c[i] == 'i' || c[i] == 'e' || c[i] == 'a' || c[i] == 'y';
        }
        System.out.println(sb.toString());
    }
}
