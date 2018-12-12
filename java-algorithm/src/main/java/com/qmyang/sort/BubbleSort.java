package com.qmyang.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 */
public class BubbleSort {

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

    private void swap(int[] a, int i, int j) {
        int tmp = a[j];
        a[j] = a[i];
        a[i] = tmp;
    }

    void print(int[] a) {
        System.out.print("[");
        for(int i = 0; i < a.length; i++){
            System.out.print(a[i]);
            if (i != a.length - 1) {
                System.out.print(",");
            }

        }
        System.out.print("]");
        System.out.println();
    }

    public static void main(String[] args) {
        int[] a = {4, 2, 1, 5, 3, 7, 9, 8, 0, 6};
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.sort(a);
    }
}
