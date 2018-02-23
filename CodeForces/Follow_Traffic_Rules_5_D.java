
/**
 * Date: 17 Feb, 2018
 * Link : http://codeforces.com/problemset/problem/5/D
 *
 * @author Prasad-Chaudhari
 * @email prasadc8897@gmail.com
 */
import java.util.Scanner;

public class Follow_Traffic_Rules_5_D {

    public static void main(String[] args) {
        // TODO code application logic here
        Scanner in = new Scanner(System.in);
        double a = in.nextInt();
        double v = in.nextInt();
        double l = in.nextInt();
        double d = in.nextInt();
        double w = in.nextInt();
        double time = 0;
        if (v <= w) {
            if ((v * v) > 2*l*a) {
                time = Math.sqrt(2 * l / a);
            } else {
                time = v / a;
                double s = v * v / (2 * a);
                time += (l - s) / v;
            }
        } else {
            if (2 * a * d <= w*w) {
                if(v*v>=2*a*l){
                    time = Math.sqrt(2*l/a);
                }
                else{
                    time = v/a;
                    double s = (v*v)/(2*a);
                    time += (l-s)/v;
                }
            }
            else{
                if(v*v-w*w>2*a*(l-d)){
                    double min = w;
                    double max = v;
                    double mid = (max+min)/2;
                    double s = 0;
                    while(Math.abs(s-(l-d))>.00000001){
                        s = (mid*mid-w*w)/(2*a);
                        if(s>(l-d)){
                            max = mid;
                        }
                        else{
                            min = mid;
                        }
                        mid= (max+min)/2;
                    }
                    time += (mid-w)/a;
                }
                else{
                    time = (v-w)/a;
                    double s = (v*v-w*w)/(2*a);
                    time+= (l-d-s)/v;
                }
                if(a*d+(w*w)/2<=v*v){
                    double max = v ;
                    double min = w;
                    double mid = (v+w)/2;
                    double s=0;
                    while(Math.abs(s-d)>.000000001){
                        s = (mid*mid/(2*a))+(mid*mid-w*w)/(2*a);
                        if(s>d){
                            max = mid;
                        }
                        else{
                            min = mid;
                        }
                        mid = (max+min)/2;
                    }
                    time+=(2*mid-w)/a;
                }
                else{
                    time += v/a;
                    time += (v-w)/a;
                    double s = d - (v*v)/(2*a) - (v*v-w*w)/(2*a);
                    time+= s/v;
                }
            }
        }
        System.out.println(time);
    }
}