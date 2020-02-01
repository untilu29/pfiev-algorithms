/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CardProblem;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author MyPC
 */
public class CardProblem {

    int N = 1000;
    int[] f = new int[N];
    int[] cathe;
    List<int[]> quanthe = new LinkedList<int[]>();
    Random rd = new Random();

    public CardProblem() {
        khoitao();
        for (int i = 0; i < N; i++) {
            danhgia();
            print();
            luachon();
            laighep();
            dotbien();
        }
    }

    private void khoitao() {
        for (int k = 0; k < N; k++) {
            cathe = new int[11];
            for (int i = 1; i < 11; i++) {
                cathe[i] = rd.nextInt(2);
            }
            quanthe.add(cathe);
        }
    }

    private void danhgia() {
        for (int i = 0; i < N; i++) {
            int tong = 0;
            int tich = 1;
            cathe = quanthe.get(i);
            for (int j = 1; j < 11; j++) {
                if (cathe[j] == 0) {
                    tong += j;
                } else {
                    tich *= j;
                }
            }
            f[i] = Math.abs(tong - 36) + 360/tich;
        }
    }

    private void luachon() {
        int[] tmp = f.clone();
        Arrays.sort(tmp);
        int nguong = tmp[95 * N / 100];
        for (int i = 0; i < N; i++) {
            if (f[i] > nguong) {
                quanthe.set(i, quanthe.get(rd.nextInt(N)));
            }
        }
    }

    private void laighep() {
        int lanlai = N;
        for (int i = 0; i < lanlai; i++) {
            int cha = rd.nextInt(N);
            int me = rd.nextInt(N);
            for (int j = 1; j < 11; j++) {
                if (rd.nextBoolean()) {
                    int tmp = quanthe.get(cha)[j];
                    quanthe.get(cha)[j] = quanthe.get(me)[j];
                    quanthe.get(me)[j] = tmp;
                }
            }
        }
    }

    private void dotbien() {
        int ind = rd.nextInt(N);
        int ptu = rd.nextInt(10) + 1;
        quanthe.get(ind)[ptu] = 1 - quanthe.get(ind)[ptu];
    }

    private void print() {
        int min = f[0];
        int idx = 0;
        for (int i = 0; i < N; i++) {
            if (min > f[i]) {
                min = f[i];
                idx = i;
            }
        }

        for (int i = 1; i < 11; i++) {
            System.out.print(quanthe.get(idx)[i]);
        }

        System.out.println("");
    }

    public static void main(String[] args) {
        new CardProblem();
    }
}
