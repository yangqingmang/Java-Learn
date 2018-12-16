package com.qmyang.sort;

public abstract class Sort {
    protected void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    protected void print(int[] a) {
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

}
