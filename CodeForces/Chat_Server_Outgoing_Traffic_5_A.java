
/**
 * Date: 14 Feb, 2018
 * Link : http://codeforces.com/problemset/problem/5/A
 * 
 * @author Prasad-Chaudhari
 * @email prasadc8897@gmail.com
 */
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Chat_Server_Outgoing_Traffic_5_A {

    public static void main(String[] args) {
        // TODO code application logic here
        Scanner in = new Scanner(System.in);
        Set<String> s = new HashSet<>();
        int ans = 0;
        for(int i = 0;i<100;i++){
            if(!in.hasNextLine()){
                break;
            }
            String c = in.nextLine();
            switch (c.charAt(0)) {
                case '+':
                    s.add(c.substring(1));
                    break;
                case '-':
                    s.remove(c.substring(1));
                    break;
                default:
                    int index = c.indexOf(':');
                    ans+=s.size()*c.substring(index+1).length();
                    break;
            }
        }
        System.out.println(ans);
    }
}
