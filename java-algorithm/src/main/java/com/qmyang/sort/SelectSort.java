package com.qmyang.sort;

public class SelectSort extends Sort {

    public void sort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[min]) {
                    min = j;
                }
            }
            swap(a, i, min);
        }
        print(a);
    }

    public static void main(String[] args) {
        int[] a = {3, 1,4, 5, 7, 9, 8, 0, 6, 2};
        SelectSort selectSort = new SelectSort();
        selectSort.sort(a);
    }
}
