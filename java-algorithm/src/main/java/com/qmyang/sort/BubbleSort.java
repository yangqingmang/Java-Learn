package com.qmyang.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 */
public class BubbleSort extends Sort {

    public void sort(int[] a) {
        if (a.length <= 0) {
            return;
        }
        for (int i = 0; i < a.length - 1; i++) {
            boolean swapFlag = false;
            for (int j = 0; j < a.length - 1; j++) {
                if (a[j] > a[j + 1]) {
                    swap(a, j, j + 1);
                    swapFlag = true;
                }
            }
            if (!swapFlag) {
                break;
            }
            print(a);
        }
    }

    public static void main(String[] args) {
        int[] a = {4, 2, 1, 5, 3, 7, 9, 8, 0, 6};
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.sort(a);
    }
}
