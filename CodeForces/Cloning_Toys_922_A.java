//package CodeForces;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package javaapplication4;

import java.util.Scanner;

/**
 * Link : http://codeforces.com/contest/922/problem/A
 * Date : 08/02/2018
 * @author PRASAD
 */
public class Cloning_Toys_922_A {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner in = new Scanner(System.in);
        int x = in.nextInt();
        int y = in.nextInt();
        if(y==0){
            System.out.println("No");
            System.exit(0);
        }
        if(y==1&&x!=0){
            System.out.println("No");
            System.exit(0);
        }
        if(x<y-1){
            System.out.println("No");
            System.exit(0);
        }
        x = x - (y-1);
        if(x%2==0){
            System.out.println("Yes");
        }
        else{
            System.out.println("No");
        }
    }
    
}
