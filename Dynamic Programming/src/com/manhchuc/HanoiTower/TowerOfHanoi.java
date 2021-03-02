/*
Muốn đưa n khối tròn từ cột A (cột nguồn) sang cột C (cột đích) thông qua cột B (cột trung gian) thì: 
1. Đưa (n-1) khối tròn từ A qua B
2. Đưa 1 khối tròn từ A qua C 
3. Và cuối cùng là đưa (n-1) khối tròn từ B qua C. 
Còn (n-1) khối tròn từ A sang B thì làm sao mà đưa? 
Đơn giản, khi đó xem A là cột nguồn, B là cột đích và C là cột trung gian. 
Việc tiến hành tương tự, đưa (n-2) khối từ cột nguồn qua cột trung gian, 
1 khối từ cột nguồn sang cột đích và cuối cùng là (n-2) khối từ cột trung gian sang cột đích. 
 */
package com.manhchuc.HanoiTower;

/**
 * @LMC
 */
public class TowerOfHanoi {

    public static void main(String[] args) {
        int n = 5;
        processHanoi(n, 1, 3);
    }

    public static void processHanoi(int numDisk, int fromPeg, int toPeg) {
        int otherPeg;
        if (numDisk == 1) {
            System.out.println("Move the disk from " + fromPeg + " ---> " + toPeg);
        } else {
            otherPeg = 6 - fromPeg - toPeg; /// Chỉ số cột tổng là 6 thì cột còn lại là 6 trừ ra
            processHanoi(numDisk - 1, fromPeg, otherPeg);
            processHanoi(1, fromPeg, toPeg);
            processHanoi(numDisk - 1, otherPeg, toPeg);
        }
    }
}
