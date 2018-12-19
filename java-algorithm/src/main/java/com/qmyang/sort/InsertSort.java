package com.qmyang.sort;

/**
 * 插入排序
 * 逻辑，如果需要插入的数据小于前面的数字，则姜当前数字和前一个数字交换
 * 继续判断当前数字和前一个数字
 */
public class InsertSort extends Sort {

    public void sort(int[] a) {
        int length = a.length;
        for (int i = 1; i < length; i++) {
            int j = i;
            while (j > 0 && a[j] < a[j - 1]) {
                swap(a, j, j - 1);
                j--;
            }
        }
        print(a);
    }

    public static void main(String[] args) {
        int[] a = {3, 1,4, 5, 7, 9, 8, 0, 6, 2};
        InsertSort insertSort = new InsertSort();
        insertSort.sort(a);
    }
}
