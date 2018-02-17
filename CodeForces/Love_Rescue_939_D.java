
/**
 * Date: 17 Feb, 2018
 * Link: http://codeforces.com/contest/939/problem/D
 * 
 * @author Prasad-Chaudhari
 * @email prasadc8897@gmail.com
 */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Love_Rescue_939_D {

    public static void main(String[] args) {
        // TODO code application logic here
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        char c1[] = in.next().toCharArray();
        char c2[] = in.next().toCharArray();
        UnionFind uf = new UnionFind(26-1);
        int count=0;
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<n;i++){
            if(uf.find(c1[i]-'a')!=uf.find(c2[i]-'a')){
                uf.union(uf.find(c1[i]-'a'), uf.find(c2[i]-'a'));
                sb.append(c1[i]).append(" ").append(c2[i]).append('\n');
                count++;
            }
        }
        System.out.println(count);
        System.out.println(sb.toString());
    }
}
class UnionFind {

    private int noOfComponents,n;
    private final int[] component;
    private final int[] size;
    ArrayList<LinkedList<Integer>> members;

    public UnionFind(int p) {
        n = p;
        noOfComponents = n;
        component = new int[n + 1];
        size = new int[n + 1];
        members = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            component[i] = i;
            size[i] = 1;
            members.add(new LinkedList<Integer>());
            members.get(i).add(i);
        }
    }

    public int find(int k) {
        return component[k];
    }

    public void union(int a, int b) {
        if (size[a] > size[b]) {
            a = a ^ b;
            b = a ^ b;
            a = a ^ b;
        }
        LinkedList<Integer> membersofa = members.get(a);
        LinkedList<Integer> membersofb = members.get(b);
        for (int i = 0; i < size[a]; i++) {
            int member = membersofa.removeFirst();
            component[member] = component[b];
            membersofb.add(member);
        }
        size[b] = size[b] + size[a];
        noOfComponents--;
    }
    
    public int noOfComponents(){
        return noOfComponents;
    }
}
