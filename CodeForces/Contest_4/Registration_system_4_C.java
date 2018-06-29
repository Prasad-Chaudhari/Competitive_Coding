
/**
 * Date: 10 Feb, 2018
 * Link : http://codeforces.com/problemset/problem/4/C
 *
 * @author Prasad-Chaudhari
 * @email prasadc8897@gmail.com
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Registration_system_4_C {

    public static void main(String[] args) {
        // TODO code application logic here
        Scanner in = new Scanner(System.in);
        Map<String, Integer> map = new HashMap<String, Integer>();
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            String s = in.next();
            if (map.containsKey(s)) {
                System.out.println(s + "" + map.get(s));
                map.put(s, map.get(s) + 1);
            } else {
                map.put(s, 1);
                System.out.println("OK");
            }
        }
    }
}
