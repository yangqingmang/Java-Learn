package com.qmyang.sort;

public class InsertSort2 extends Sort {

    public void sort(int[] a) {
        int length = a.length;
        for (int i = 1; i < length; i++) {
            int value = a[i];
            int j = i;
            while (j > 0 && value < a[j - 1]) {
                a[j] = a[j - 1];
                j--;
            }
            a[j] = value;
        }
        print(a);
    }

    public static void main(String[] args) {
        int[] a = {3, 1,4, 5, 7, 9, 8, 0, 6, 2};
        InsertSort2 insertSort = new InsertSort2();
        insertSort.sort(a);
    }

}
