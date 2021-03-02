package NguaDiTuanIQ;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class NguaDiTuan {

    int N = 10000; // Luong ca the
    int[] f = new int[N];
    int[] a = {2, 1, -1, -2, -2, -1, 1, 2};
    int[] b = {1, 2, 2, 1, -1, -2, -2, -1};
    List<int[][]> nghiem = new ArrayList(N);
    Random rnd;


    public NguaDiTuan() {
        rnd = new Random();
        khoitao();
        for (int i = 0; i < 100; i++) {
            print();
            danhgia();
            luachon();
//            laighep();
//            dotbien();
        }
    }

    public boolean condNess(int x0, int y0) {
        return !(x0 == 3 && y0 == 0) || (x0 == 3 && y0 == 3);
    }

    private void khoitao() {
        for (int i = 0; i < N; i++) {
            int[][] table = new int[4][4];

//        khoi tao vi tri ban dau cua ngua
            int x0, y0;
            do {
                x0 = rnd.nextInt(4);
                y0 = rnd.nextInt(4);
            } while (!condNess(x0, y0));

            int enough = 0;
            while (enough < 14) {
                int idx = rnd.nextInt(8);
                int u = x0 + a[idx];
                int v = x0 + b[idx];
                if (u >= 0 && v >= 0 && u < 4 && v < 4 && condNess(u, v)) {
                    table[u][v] += 1;
                    x0 = u;
                    y0 = v;
                    enough++;
                }
            }
            nghiem.add(table);
        }

////        In ra thu
//        for (int i = 0; i < 1000; i++) {
//            int[][] table = nghiem.get(i);
//            for (int j = 0; j < 4; j++) {
//                for (int k = 0; k < 4; k++) {
//                    System.out.print(table[j][k] + " ");
//                }
//                System.out.println("");
//            }
//            System.out.println("");
//
//        }
    }

    private void danhgia() {
        for (int i = 0; i < N; i++) {
            int fitness = 0;
            int[][] table = nghiem.get(i);
            int max = table[0][0];
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    fitness += table[j][k] == 0 ? 1 : 0;
                    if (max < table[j][k]) {
                        max = table[j][k];
                    }
                }
            }
            f[i] = fitness;// * 2 + max;
        }

    }

    private void luachon() {
        int[] tmp = f.clone();
        Arrays.sort(tmp);
        int nguong = tmp[80 * N / 100];
        for (int i = 0; i < N; i++) {
            if (f[i] > nguong) {
                nghiem.set(i, nghiem.get(rnd.nextInt(N)));
            }
        }
    }

    private void laighep() {
        int lanlai = N;
        for (int i = 0; i < lanlai; i++) {
            int cha = rnd.nextInt(N);
            int me = rnd.nextInt(N);
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    if (rnd.nextBoolean()) {
                        int tmp = nghiem.get(cha)[j][k];
                        nghiem.get(cha)[j][k] = nghiem.get(me)[j][k];
                        nghiem.get(me)[j][k] = tmp;
                    }
                }
            }
        }
    }

//    private void dotbien() {
//        int index=rnd.nextInt(N);
//        int ptu=rnd.nextInt()
//    }

    public static void main(String[] args) {
        new NguaDiTuan();
    }

    private void print() {
        int min = f[0];
        int idxMin = 0;
        for (int i = 0; i < N; i++) {
            if (min > f[i]) {
                min = f[i];
                idxMin = i;
            }
        }
        //        In ra thu
        int[][] table = nghiem.get(idxMin);
        for (int j = 0; j < 4; j++) {
            for (int k = 0; k < 4; k++) {
                System.out.print(table[j][k] + " ");
            }
            System.out.println("");
        }
        System.out.println("");


    }
}
