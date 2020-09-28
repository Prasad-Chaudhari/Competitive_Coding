import java.util.*;
import java.lang.*;
import java.io.*;

class newProgram5 {
    public static void main(String[] args) throws java.lang.Exception {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        long mod = 1000000007;
        while (--t >= 0) {
            int l = in.nextInt();
            int r = in.nextInt();
            long ans = 0;
            if (r <= 1000) {
                for (int i = l; i <= r; i++) {
                    for (int j = l; j <= r; j++) {
                        char[] a = ("" + i).toCharArray();
                        char[] b = ("" + j).toCharArray();
                        Arrays.sort(a);
                        Arrays.sort(b);
                        for (int k = 0; k < a.length / 2; k++) {
                            char temp = a[k];
                            a[k] = a[a.length - 1 - k];
                            a[a.length - 1 - k] = temp;
                        }
                        for (int k = 0; k < b.length / 2; k++) {
                            char temp = b[k];
                            b[k] = b[b.length - 1 - k];
                            b[b.length - 1 - k] = temp;
                        }
                        int n = Math.max(a.length, b.length);
                        for (int k = 0; k < n; k++) {
                            int p1 = k < a.length ? a[k] - '0' : 0;
                            int p2 = k < b.length ? b[k] - '0' : 0;
                            ans += Math.abs(p1 - p2);
                        }
                    }
                }
            }
            System.out.println(ans % mod);
        }
    }
}
