package com.wjs.produce.likou;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public static int pivotIndex(int[] nums) {
        int total = Arrays.stream(nums).sum();
        int sum = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (2 * sum + nums[i] == total) {
                return i;
            }
            sum += nums[i];
        }
        return -1;
    }

    public static void main(String[] args) {

        System.out.println(pivotIndex(new int[]{1,7,3,6,5,6}));
    }
}
