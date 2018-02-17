
/**
 * Date: 14 Feb, 2018
 * Link : http://codeforces.com/problemset/problem/5/B
 *
 * @author Prasad-Chaudhari
 * @email prasadc8897@gmail.com
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Center_Alignment_5_B {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        LinkedList<String> l = new LinkedList<>();
        int max = 0;
        while (br.ready()) {
            String s = br.readLine();
            l.add(s);
            if (max < s.length()) {
                max = s.length();
            }
        }
        System.out.print("*");
        for (int i = 0; i < max; i++) {
            System.out.print("*");
        }
        System.out.print("*");
        System.out.println();
        for (String s : l) {
            int li = (max - s.length()) / 2;
            if ((max - s.length()) % 2 == 1) {
                li++;
            }
            System.out.print("*");
            for (int i = 0; i < li; i++) {
                System.out.print(" ");
            }
            System.out.print(s);
            for (int i = 0; i < max - s.length() - li; i++) {
                System.out.print(" ");
            }
            System.out.print("*");
            System.out.println();
        }
        System.out.print("*");
        for (int i = 0; i < max; i++) {
            System.out.print("*");
        }
        System.out.print("*");
        System.out.println();

    }
}
