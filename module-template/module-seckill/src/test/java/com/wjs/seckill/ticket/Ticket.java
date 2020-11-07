package com.wjs.seckill.ticket;

/**
 * @ClassName ticket
 * @Description: TODO
 * @Author wjs
 * @Date 2020/10/12
 * @Version V1.0
 **/
public class Ticket {

    private int[][] seat1 = new int[3][4];
    private int[][] seat2 = new int[4][5];
    private int[][] seat3 = new int[2][3];
    private int[][] seat4 = new int[3][4];

    private void init(int[][] seat) {
        for (int i = 0; i < seat.length; i++) {
            for (int j = 0; j < seat[i].length; j++) {
                seat[i][j] = 0;
            }
        }
    }

    public void init() {
        init(seat1);
        init(seat2);
        init(seat3);
        init(seat4);
    }

    private void print(int[][] seat){
        for (int i = 0; i < seat.length; i++) {
            for (int j = 0; j < seat[i].length; j++) {
                seat[i][j] = 0;
            }
        }
    }

    public void print(){
        print(seat1);
        print(seat2);
        print(seat3);
        print(seat4);
    }
}
