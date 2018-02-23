
/**
 * Date: 17 Feb, 2018
 * Link : http://codeforces.com/problemset/problem/5/C
 *
 * @author Prasad-Chaudhari
 * @email prasadc8897@gmail.com
 */
import java.util.Scanner;
import java.util.Stack;

public class Longest_Regular_Bracket_Sequence_5_C {

    public static void main(String[] args) {
        // TODO code application logic here
        Scanner in = new Scanner(System.in);
        char c[] = in.next().toCharArray();
        int n = c.length;
        boolean ch[] = new boolean[n];
        Stack<Integer> st = new Stack<Integer>();
        for (int i = 0; i < n; i++) {
            if (c[i] == '(') {
                st.push(i);
            } else {
                if (!st.isEmpty()) {
                    int index = st.pop();
                    ch[i] = true;
                    ch[index] = true;
                }
            }
        }
        int max = 0;
        int l = 0;
        int curr = 0;
        for (int i = 0; i < n; i++) {
            if (ch[i]) {
                l++;
            } else {
                if(l>=max){
                    if(max==l){
                        curr++;
                    }
                    else{
                        curr=1;
                    }
                    max = l;
                }
                l = 0;
            }
        }
        if(l>=max){
            if(max==l){
                curr++;
            }
            else{
                curr=1;
            }
            max = l;
        }
        if (max == 0) {
            System.out.println("0 1");
        } else {
            System.out.println(max + " " + curr);
        }
    }
}
