/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package CodeForces;

import java.util.Scanner;

/**
 *
 * @author PRASAD
 */
public class Magic_Forest_922_B {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int count = 0;
        for(int i=1;i<=n;i++){
            for(int j=i;j<=n;j++){
                int c = i^j;
                if(c<=i||c<=j||c>n){
                    continue;
                }
                if(i+j>c){
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}
