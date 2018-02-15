
/**
 * Date: 15 Feb, 2018
 * Link : http://codeforces.com/contest/932/problem/A
 * 
 * @author Prasad-Chaudhari
 * @email prasadc8897@gmail.com
 */
import java.util.Scanner;

public class Palindromic_Supersequence_932_A {

    public static void main(String[] args) {
        // TODO code application logic here
        Scanner in = new Scanner(System.in);
        String s = in.next();
        StringBuilder sb = new StringBuilder(s);
        System.out.println(s+""+sb.reverse());
    }
}
