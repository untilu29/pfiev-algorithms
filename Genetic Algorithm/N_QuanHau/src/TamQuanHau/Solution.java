/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TamQuanHau;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author DINHTUAN
 */
public class Solution {

    private ArrayList<StateQueens> listStateQueens;
    private int limit;// size của ma trận
    private int tempNumber, tempLocation, test;
    private int numberStateQueensBegin, tempRandom, tempRandomLocationBegin, tempRandomLocationCrossover, m;
    private StateQueens stateQueen, tempState, tempState1, tempState2;
    private int[][] chessboard;

    public Solution(int limit) {
        this.limit = limit;

        test = 0;
        listStateQueens = new ArrayList<StateQueens>();
        numberStateQueensBegin = (int) Math.pow(getLimit(), 3);
        tempState1 = new StateQueens(getLimit());
        tempState2 = new StateQueens(getLimit());

        createStateQueensBegin();
        sortStateQueen();
        cross();
    }

    private void createStateQueensBegin() {
        for (int i = 0; i < getNumberStateQueensBegin(); i++) {
            stateQueen = new StateQueens(getLimit());
            System.out.println("State begin:");
            printState(stateQueen);
            listStateQueens.add(stateQueen);
        }
    }

    private void sortStateQueen() {
        boolean test = true;

        while (test) {
            test = false;
            for (int i = 0; i < getListStateQueens().size() - 2; i++) {
                if (getListStateQueens().get(i).getCost() > getListStateQueens().get(i + 1).getCost()) {
                    tempState = getListStateQueens().get(i);
                    getListStateQueens().set(i, getListStateQueens().get(i + 1));
                    getListStateQueens().set(i + 1, tempState);
                    test = true;
                }
            }
        }
    }

    private void cross() {
        if (testCost(getListStateQueens().get(0))) {
            return;
        }


        for (StateQueens s1 : getListStateQueens()) {
            for (StateQueens s2 : getListStateQueens()) {
                System.out.println("Test: " + ++test);

                crossover(s1, s2);

                processMutation();

                System.out.println("TempState1: ");
                printState(tempState1);
                System.out.println("TempState2: ");
                printState(tempState2);

                if (testCost(tempState1)) {
                    return;
                }

                if (testCost(tempState2)) {
                    return;
                }
            }
        }

        cross();
    }

    private void crossover(StateQueens s1, StateQueens s2) {
        Random random = new Random();
        tempRandomLocationCrossover = random.nextInt(getLimit() - 1) + 1;
        tempRandomLocationBegin = random.nextInt(tempRandomLocationCrossover);

        m = 0;
        while (m != getLimit()) {
            for (int i = tempRandomLocationBegin; i < tempRandomLocationCrossover; i++) {
                tempState1.setLocationQueen(m, s1.getLocationQueen(i));
                tempState2.setLocationQueen(m, s2.getLocationQueen(i));
                m++;
            }

            for (int j = tempRandomLocationCrossover; j < getLimit(); j++) {
                tempState1.setLocationQueen(m, s2.getLocationQueen(j));
                tempState2.setLocationQueen(m, s1.getLocationQueen(j));
                m++;
            }

            for (int k = 0; k < tempRandomLocationBegin; k++) {
                tempState1.setLocationQueen(m, s2.getLocationQueen(k));
                tempState2.setLocationQueen(m, s1.getLocationQueen(k));
                m++;
            }
        }
    }

    private void processMutation() {
        if (tempState1.testLocationEqual() != -1) {
            tempState1 = mutation(tempState1);
        }

        if (tempState2.testLocationEqual() != -1) {
            tempState2 = mutation(tempState2);
        }
    }

    private StateQueens mutation(StateQueens state) {
        tempNumber = 0;
        while ((tempLocation = state.testLocationEqual()) != -1) {
            state.setLocationQueen(tempLocation, tempNumber++);

            if (tempNumber == getLimit()) {
                tempNumber = 0;
            }
        }
        return state;
    }

    private boolean testCost(StateQueens state) {
        state.sumCost();
        if (state.getCost() == 0) {
            System.out.println("Result: ");
            printState(state);
            return true;
        }
        return false;
    }

    private void printState(StateQueens state) {
        chessboard = new int[getLimit()][getLimit()];
        for (int i = 0; i < getLimit(); i++) {
            for (int j = 0; j < getLimit(); j++) {
                if (state.getLocationQueen(i) == j) {
                    chessboard[i][j] = 1;
                }
                System.out.print(chessboard[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public ArrayList<StateQueens> getListStateQueens() {
        return listStateQueens;
    }

    public void setListStateQueens(ArrayList<StateQueens> listStateQueens) {
        this.listStateQueens = listStateQueens;
    }

    public int getNumberStateQueensBegin() {
        return numberStateQueensBegin;
    }

    public void setNumberStateQueensBegin(int numberStateQueensBegin) {
        this.numberStateQueensBegin = numberStateQueensBegin;
    }

    public StateQueens getStateQueen() {
        return stateQueen;
    }

    public void setStateQueen(StateQueens stateQueen) {
        this.stateQueen = stateQueen;
    }
}
