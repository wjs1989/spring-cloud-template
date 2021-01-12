package com.wjs.seata.lottery;

public class HistoryLottery {

    public void init(){

    }

    public class HLottery{
        private int[] red = new int[5];
        private int[] blue = new int[2];

        public int[] getRed() {
            return red;
        }

        public int[] getBlue() {
            return blue;
        }
    }

}
