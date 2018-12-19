package com.qmyang.sort;

/**
 * 快速排序2
 *
 * @author yangqingmang
 * @version 1.0
 * @date Create in 2018-12-19 17:43
 */
public class QuickSort2 extends Sort {

    public void sort(int[] a) {
        quickSort(a, 0, a.length - 1);
        print(a);
    }

    void quickSort(int[] a, int p, int r) {
        if (p < r) {
            int i = p, j = r;

            int pivot = a[j];

            while (i < j) {

                while (i < j && a[i] <= pivot) {
                    i++;
                }

                a[j] = a[i];

                while (i < j && a[j] > pivot) {
                    j--;
                }
                a[i] = a[j];
            }
            a[j] = pivot;
            quickSort(a, p, i - 1);
            quickSort(a, i + 1, r);
        }
    }

    public static void main(String[] args) {
        int[] a = {3, 1, 4, 5, 7, 9, 8, 0, 6, 2};
        QuickSort2 quickSort = new QuickSort2();
        quickSort.sort(a);
    }
}
