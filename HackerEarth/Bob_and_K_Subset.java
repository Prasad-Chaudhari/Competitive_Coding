
/**
 * Date: 25 May, 2018
 * Link: https://www.hackerearth.com/challenge/competitive/may-circuits-18/algorithm/bob-and-subset-23f0729c/
 *
 * @author Prasad-Chaudhari
 * @email prasadc8897@gmail.com
 */
import java.util.*;

public class Bob_and_K_Subset {

    public static void main(String[] args) throws java.lang.Exception {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int a[] = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        ArrayList<Set<Integer>> ss = new ArrayList<Set<Integer>>();
        for (int i = 0; i <= k; i++) {
            ss.add(new HashSet<Integer>());
        }
        ss.get(1).add(a[0]);
        for (int i = 1; i < n; i++) {
            for (int j = k - 1; j >= 1; j--) {
                for (int l : ss.get(j)) {
                    ss.get(j + 1).add(a[i] | l);
                }
            }
            ss.get(1).add(a[i]);
        }
        boolean b[] = new boolean[2048];
        for (Set<Integer> s : ss) {
            for (int i : s) {
                b[i] = true;
            }
        }
        int count = 0;
        for (boolean b2 : b) {
            if (b2) {
                count++;
            }
        }
        System.out.println(count);
    }
}
