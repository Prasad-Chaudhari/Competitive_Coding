package CodeForces;


/**
 * Date: 9 Feb, 2018
 * Link : http://codeforces.com/problemset/problem/4/A
 * @author Prasad-Chaudhari
 * @email prasadc8897@gmail.com
 */
import java.util.Scanner;

public class Watermelon_4_A {
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner in = new Scanner(System.in);
        int w = in.nextInt();
        if(w%2==0){
            if(w==2){
                System.out.println("NO");
            }
            else{
                System.out.println("YES");
            }
        }
        else{
            System.out.println("NO");
        }
    }
}
