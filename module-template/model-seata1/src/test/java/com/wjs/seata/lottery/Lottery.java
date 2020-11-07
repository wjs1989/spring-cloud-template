package com.wjs.seata.lottery;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class Lottery {
    private int[] red = new int[5];
    private int[] blue = new int[2];

    public void init() {
        Set<Integer> redNumber = new TreeSet<>();
        do {
            redNumber.add(new Random().nextInt(33) + 1);
        } while (redNumber.size() < 5);

        Set<Integer> blueNumber = new TreeSet<>();
        do {
            blueNumber.add(new Random().nextInt(15) + 1);
        } while (blueNumber.size() < 2);

        Integer[] r = redNumber.toArray(new Integer[5]);
        for (int i = 0; i < r.length; i++) {
            red[i] = r[i];
        }

        Integer[] b = blueNumber.toArray(new Integer[2]);
        for (int i = 0; i < b.length; i++) {
            blue[i] = b[i];
        }
    }

    public String toString() {
        return String.format("%s %s %s %s %s  %s %s",
                red[0], red[1], red[2], red[3], red[4], blue[0], blue[1]);
    }
}
