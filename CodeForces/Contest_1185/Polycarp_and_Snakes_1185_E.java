
import java.util.*;

public class Polycarp_and_Snakes_1185_E {

    public static void main(String[] args) throws java.lang.Exception {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        outer:
        while (t-- > 0) {
            int n = in.nextInt();
            int m = in.nextInt();
            char c[][] = new char[n][m];
            for (int i = 0; i < n; i++) {
                c[i] = in.next().toCharArray();
            }
            Data d[][] = new Data[26][Math.max(n, m)];
            int indexs[] = new int[26];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (c[i][j] != '.') {
                        if (indexs[c[i][j] - 'a'] == d[0].length) {
                            System.out.println("NO");
                            continue outer;
                        }
                        d[c[i][j] - 'a'][indexs[c[i][j] - 'a']++] = new Data(i, j);
                    }
                }
            }

            for (int i = 0; i < 26; i++) {
                boolean p = true;
                for (int j = 0; j < indexs[i]; j++) {
                    p &= d[i][0].a == d[i][j].a;
                }

                boolean q = true;
                for (int j = 0; j < indexs[i]; j++) {
                    q &= d[i][0].b == d[i][j].b;
                }

                if (!(p || q)) {
                    System.out.println("NO");
                    continue outer;
                }
            }

            String s[] = new String[26];
            int count = 0;
            for (int j = 26 - 1; j >= 0; j--) {
                if (indexs[j] == 0) {

                    if (j < 25) {
                        if (s[j + 1] != null) {
                            s[j] = s[j + 1];
                            count++;
                        }
                    }
                } else {
                    int x1 = Integer.MAX_VALUE;
                    int y1 = Integer.MAX_VALUE;
                    int x2 = Integer.MIN_VALUE;
                    int y2 = Integer.MIN_VALUE;
                    for (int i = 0; i < indexs[j]; i++) {
                        Data dd = d[j][i];
                        x1 = Math.min(x1, dd.a);
                        y1 = Math.min(y1, dd.b);
                        x2 = Math.max(x2, dd.a);
                        y2 = Math.max(y2, dd.b);
                    }

                    for (int i = x1; i <= x2; i++) {
                        for (int k = y1; k <= y2; k++) {
                            if (c[i][k] == '.') {
                                System.out.println("NO");
                                continue outer;
                            } else if (c[i][k] == j + 'a' || c[i][k] == '?') {

                            } else {
                                System.out.println("NO");
                                continue outer;
                            }
                            c[i][k] = '?';
                        }
                    }
                    x1++;
                    x2++;
                    y1++;
                    y2++;
                    count++;
                    s[j] = x1 + " " + y1 + " " + x2 + " " + y2;
                }
            }
            System.out.println("YES");
            System.out.println(count);
            for (int i = 0; i < count; i++) {
                System.out.println(s[i]);
            }
        }
    }

    static class Data {

        int a, b;

        public Data(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
}
