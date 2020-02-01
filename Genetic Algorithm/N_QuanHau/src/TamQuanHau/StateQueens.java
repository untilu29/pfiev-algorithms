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
public class StateQueens {
    private ArrayList<Integer> listLocationQueens; // vị trí những quân hậu
    private ArrayList<Integer> state; // trạng thái những quân hậu trên bàn cờ theo cột
    private int cost = 0;// chi phí quân hậu là tổng số cặp hậu ăn nhau
    private int numberQueens;// số quân hậu được truyền vào
    private int x, y; // x là vị trí hàng và y là vị trí cột của quân hậu trên bàn cờ

    public StateQueens(int numberQueens) {
        this.numberQueens = numberQueens;

        listLocationQueens = new ArrayList<Integer>();
        state = new ArrayList<Integer>();

        creatListLocationQueens();
        createStateQueens();
        sumCost();
    }

    private void creatListLocationQueens(){
        for(int i = 0; i < getNumberQueens(); i++){
            getListLocationQueens().add(i);
        }
    }

    private void createStateQueens(){
        Random random = new Random();
        while (!getListLocationQueens().isEmpty()) {
            getState().add((Integer) getListLocationQueens().remove(random.nextInt(getListLocationQueens().size())));
        }
    }

    public void sumCost(){
        setCost(0);
        for(int i = 0; i < getNumberQueens(); i++){
            x = i;
            y = (Integer) getState().get(i);
            diagonalNorthWest(x, y);
            diagonalSouthEast(x, y);
            diagonalSouthWest(x, y);
            diagonalNorthhEast(x, y);
        }
        setCost(getCost()/2);
    }

    private void diagonalNorthWest(int m, int n){ //đường chéo hướng Tây - Bắc
        while((m-- > 0) && (n-- > 0)){
            if(n == getState().get(m)){
                setCost(getCost() + 1);
            }
        }
    }

    private void diagonalSouthEast(int m, int n){ //đường chéo hướng Đông - Nam
        while((m++ < getNumberQueens() - 1) && (n++ < getNumberQueens() - 1)){
            if(n == getState().get(m)){
                setCost(getCost() + 1);
            }
        }
    }

    private void diagonalSouthWest(int m, int n){ //đường chéo hướng Tây - Nam
        while((m++ < getNumberQueens() - 1) && (n-- > 0)){
            if(n == getState().get(m)){
                setCost(getCost() + 1);
            }
        }
    }

    private void diagonalNorthhEast(int m, int n){ //đường chéo hướng Đông - Bắc
        while((m-- > 0) && (n++ < getNumberQueens() - 1)){
            if(getState().get(m) == n){
                setCost(getCost() + 1);
            }
        }
    }

    public int getLocationQueen(int m){
        return (Integer) getState().get(m);
    }

    public void setLocationQueen(int m, int n){
        getState().add(m, n);
        getState().remove(m + 1);
    }

    public int testLocationEqual(){
        for(int i = 0; i < getNumberQueens() - 2; i++){
            for(int j = i + 1; j < getNumberQueens(); j++){
                if(getState().get(i) == getState().get(j)){
                    return i;
                }
            }
        }
        return -1;
    }

    public ArrayList<Integer> getState() {
        return state;
    }

    public void setState(ArrayList state) {
        this.state = state;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getNumberQueens() {
        return numberQueens;
    }

    public void setNumberQueens(int numberQueens) {
        this.numberQueens = numberQueens;
    }

    public ArrayList getListLocationQueens() {
        return listLocationQueens;
    }

    public void setListLocationQueens(ArrayList listLocationQueens) {
        this.listLocationQueens = listLocationQueens;
    }

}
