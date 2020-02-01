/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deccision;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author MyPC
 */
public class Deccision {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        String x="BLacklisted";
        String y="Not2";
        Random rnd= new Random();
        System.out.println(rnd.nextBoolean()?"1."+x+"\n2."+y:"1."+y+"\n2."+x);
        Scanner in= new Scanner(System.in);
        System.out.print("----PRESS ENTER TO SOLVE----");
        in.nextLine();
        for (int i = 0; i < 40; i++) {
            Thread.sleep(500);
            System.out.print(".");
        }
        System.out.println("\nLua chon so: "+(1+(rnd.nextBoolean()?1:0)));
    }
   
    
}
