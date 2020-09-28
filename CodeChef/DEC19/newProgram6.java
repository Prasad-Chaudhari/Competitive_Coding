import java.util.*;
import java.lang.*;
import java.io.*;

class newProgram6 {
    public static void main(String[] args) throws java.lang.Exception {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (--t >= 0) {
            int n = in.nextInt();
            Data d[] = new Data[n];
            for (int i = 0; i < n; i++) {
                d[i] = new Data(in.nextInt(), in.nextInt());
            }
            Arrays.sort(d, new Comparator<Data>() {
                @Override
                public int compare(Data a, Data b) {
                    if (a.a == b.a) {
                        return Integer.compare(a.b, b.b);
                    }
                    return Integer.compare(a.a, b.a);
                }
            });
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                int count = 0;
                int x = d[i].a;
                int y = d[i].b;
                for (int j = 0; j < n; j++) {
                    if (i != j) {
                        if (y >= d[j].a && d[j].b >= x) {
                            count++;
                        }
                    }
                }
                min = Math.min(min, count);
            }
            if (min == Integer.MAX_VALUE || min == n - 1) {
                System.out.println(-1);
            } else {
                System.out.println(min);
            }
        }
    }
}

class Data {
    int a, b;

    public Data(int a, int b) {
        this.a = a;
        this.b = b;
    }
}
