package CodeForces;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Scanner;

/**
 *
 * @author PRASAD
 */
public class Shortest_path_of_the_king_3_A {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner in = new Scanner(System.in);
        String pos = in.next();
        String pos2 = in.next();
        int x1 = (pos.charAt(0)-'a');
        int y1 = (pos.charAt(1)-'0');
        int x2 = (pos2.charAt(0)-'a');
        int y2 = (pos2.charAt(1)-'0');
        String s="";
        int count=0;
        if(x2>x1){
            if(y2>y1){
                while(!(x2==x1||y2==y1)){
                    s+="RU\n";
                    x2--;
                    y2--;
                    count++;
                }
                while(y2!=y1){
                    s+="U\n";
                    y2--;
                    count++;
                }
                while (x2!=x1) {
                    s+="R\n";
                    x2--;
                    count++;
                }
            }
            else{
                while(!(x2==x1||y2==y1)){
                    s+="RD\n";
                    x2--;
                    y2++;
                    count++;
                }
                while(y2!=y1){
                    s+="D\n";
                    y2++;
                    count++;
                }
                while (x2!=x1) {
                    s+="R\n";
                    x2--;
                    count++;
                }
            }
        }
        else{
            if(y2>y1){
                while(!(x2==x1||y2==y1)){
                    s+="LU\n";
                    x2++;
                    y2--;
                    count++;
                }
                while(y2!=y1){
                    s+="U\n";
                    y2--;
                    count++;
                }
                while (x2!=x1) {
                    s+="L\n";
                    x2++;
                    count++;
                }
            }
            else{
                while(!(x2==x1||y2==y1)){
                    s+="LD\n";
                    x2++;
                    y2++;
                    count++;
                }
                while(y2!=y1){
                    s+="D\n";
                    y2++;
                    count++;
                }
                while (x2!=x1) {
                    s+="L\n";
                    x2++;
                    count++;
                }
            }
        }
        System.out.println(count+"\n"+s);
    }
    
}
