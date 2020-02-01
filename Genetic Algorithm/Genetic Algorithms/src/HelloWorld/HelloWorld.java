/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HelloWorld;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author MyPC
 */
public class HelloWorld {

    int N = 1000; // So luong ca the ban dau
    String target = "Hello World";
    int len = target.length();
    char[] character;
    int[] f; //Danh gia
    String[] quanthe; //
    Random rd;

    public HelloWorld() {
        rd = new Random();
        f = new int[N];
        character = new char[95];
        for (int i = 0; i < 95; i++) {
            character[i] = (char) (i + 32);
        }

        khoitao();
        for (int i = 0; i < N; i++) {
            danhgia();
            print();
            luachon();
            laighep();
            dotbien();
        }
    }

    public void khoitao() {
        quanthe = new String[N];
        for (int i = 0; i < N; i++) {
            char[] arr_String = new char[len];
            for (int j = 0; j < len; j++) {
                arr_String[j] = character[rd.nextInt(95)];
            }
            quanthe[i] = new String(arr_String);
        }
    }

    public void danhgia() {
        f = new int[N];
        for (int i = 0; i < N; i++) {
            int space = 0;
            for (int j = 0; j < len; j++) {
                space += Math.abs((int) target.charAt(j) - (int) quanthe[i].charAt(j));
            }
            f[i] = space;
        }
    }

    public void luachon() {
        int[] tmp = f.clone();
        Arrays.sort(tmp);
        int nguong = tmp[N * 95 / 100];
        for (int i = 0; i < N; i++) {
            if (f[i] > nguong) {
                quanthe[i] = quanthe[rd.nextInt(N)];
            }
        }

    }

    public void laighep() {
        int lanlai = N;
        for (int i = 0; i < lanlai; i++) {
            int cha = rd.nextInt(N);
            int me = rd.nextInt(N);
            for (int j = 0; j < len; j++) {
                if (rd.nextBoolean()) {
                    char tmp = quanthe[cha].charAt(j);
                    quanthe[cha] = replaceCharAt(quanthe[cha], j, quanthe[me].charAt(j));
                    quanthe[me] = replaceCharAt(quanthe[me], j, tmp);
                }
            }
        }
    }

    public void dotbien() {
        int idx = rd.nextInt(N);
        int ptu = rd.nextInt(len);
        quanthe[idx] = replaceCharAt(quanthe[idx], ptu, (char) (95 - (int) quanthe[idx].charAt(ptu)));
    }

    public static String replaceCharAt(String s, int pos, char c) {
        return s.substring(0, pos) + c + s.substring(pos + 1);
    }

    public void print() {
        int min = f[0];
        int idxMin = 0;
        for (int i = 0; i < N; i++) {
            if (min > f[i]) {
                min = f[i];
                idxMin = i;
            }
        }
        System.out.println("Ket qua: " + quanthe[idxMin]);
    }

    public static void main(String[] args) {
//        String a="Le Manh Chuc";
//        System.out.println(replaceCharAt(a, 0, 'a'));
        new HelloWorld();
    }
}
