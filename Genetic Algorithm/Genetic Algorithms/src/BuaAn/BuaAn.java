/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BuaAn;

import java.util.Arrays;
import java.util.Random;


public class BuaAn {
    int []foodValue;
    String []foodName;
    int [][]foodNutrient;
    
    Random rd;
    int [][] quanthe;
    int N; // so luong ca the ban dau
    int f[]; // danh gia
    public BuaAn() {
        N = 100;
        Random rd = new Random();
        f = new int[N];
        khoitao();
        for(int i = 0; i < 100; i = i + 1){
            danhgia();
            luachon();
            laighep();
            dotbien();
        }
    }

    private void khoitao() {  
        quanthe = new int[N][32];
        for(int i = 0; i < N; i = i + 1){
            for(int j = 0; j < 32; j = j + 1){
                quanthe[i][j] = rd.nextInt(2);
            }
        }
    }

    private void danhgia() {
        for(int i = 0; i < N; i = i + 1){
            int giatien = 0;
            for(int j = 0; j < 32; j = j + 1){
                giatien = giatien + quanthe[i][j] * foodValue[j];
            }
            int dinhduong = 0;
            for(int j = 0; j < 32; j = j + 1){
               for(int k = 0; k < 10; k = k + 1){
                    dinhduong = dinhduong + quanthe[i][j]*foodNutrient[j][k];
                }
            }
            f[i] = (10 - dinhduong)*40 + giatien;
            
        }
        print();
    }

    private void luachon() {
        int []temp = f.clone();
        Arrays.sort(temp);
        int nguong = temp[N*80/100];
        for(int i = 0; i < N; i = i + 1){
            if(f[i] > nguong ){
                int index = rd.nextInt(N);
                quanthe[i] = quanthe[index].clone();
            }
        }
    }

    private void laighep() {
        int c = 20;
        for(int i = 0; i < 20; i = i + 1){
            int cha = rd.nextInt(N);
            int me = rd.nextInt(N);
                for(int j = 0; j < 32; j = j + 1){
                int temp = quanthe[cha][j];
                quanthe[cha][j] = quanthe[me][j];
                quanthe[me][j] = temp;
            }
    }
    
}

    private void dotbien() {
        int i = 0;
        if(i == rd.nextInt(5)){
            int index = rd.nextInt(N);
            int vitri = rd.nextInt(32);
            quanthe[index][vitri] = 1 - quanthe[index][vitri];
        }
    }
    
    public void print(){
        int min = Integer.MAX_VALUE;
        int vitri = 0;
        for(int i = 0; i < N; i = i + 1){
            if(min < f[i]){
                vitri = i;
            }
        }
        int tongtien = 0;
        for(int i = 0; i < 32; i = i + 1){
            if(quanthe[vitri][i] == 1){
                System.out.print(foodName[i] + ", ");
                tongtien = tongtien + foodValue[i];             
            }
            System.out.println("Tong tien: "+tongtien);
        }
    }
}
