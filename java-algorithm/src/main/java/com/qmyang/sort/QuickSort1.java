package com.qmyang.sort;

/**
 * 快速排序
 * 逻辑：选择基准数字
 * @author yangqingmang
 * @version 1.0
 * @date Create in 2018-12-19 17:06
 */
public class QuickSort1 extends Sort {

    public void sort(int[] a) {
        quickSort(a, 0, a.length - 1);
        print(a);
    }

    void quickSort(int[] a, int p, int r) {
        if (p >= r) {
            return;
        }

        int s = partition(a, p, r);
        quickSort(a, 0, s - 1);
        quickSort(a, s + 1, r);
    }

    int partition(int[] a, int p, int r) {
        int pivot = a[r];

        int i = p;

        for (int j = p; j < r; j++) {
            if (a[j] < pivot) {
                swap(a, i, j);
                i++;
                print(a);
            }
        }
        swap(a, i, r);
        return i;
    }

    public static void main(String[] args) {
        int[] a = {3, 1, 4, 5, 7, 9, 8, 0, 6, 2};
        QuickSort1 quickSort = new QuickSort1();
        quickSort.sort(a);
    }

}
