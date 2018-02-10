
/**
 * Date: 11 Feb, 2018
 * Link : http://codeforces.com/problemset/problem/4/B
 * @author Prasad-Chaudhari
 * @email prasadc8897@gmail.com
 */
import java.util.Scanner;

public class Before_an_Exam_4_B {
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner in = new Scanner(System.in);
        int d = in.nextInt();
        int sumtime = in.nextInt();
        int min[] = new int[d];
        int max[] = new int[d];
        int minsum=0;
        int maxsum=0;
        for(int i=0;i<d;i++){
            min[i] = in.nextInt();
            minsum+=min[i];
            max[i] = in.nextInt();
            maxsum+=max[i];
        }
        if(sumtime>=minsum&&sumtime<=maxsum){
            System.out.println("YES");
            int time[] = new int[d];
            System.arraycopy(min, 0, time, 0, d);
            for(int i:time){
                sumtime-=i;
            }
            for(int i=0;i<d;i++){
                if(sumtime>=max[i]-min[i]){
                    time[i]+=(max[i]-min[i]);
                    sumtime-=(max[i]-min[i]);
                }
                else{
                    time[i]+=sumtime;
                    sumtime=0;
                }
                System.out.print(time[i]+" ");
            }
            
        }
        else{
            System.out.println("NO");
        }
    }
}
