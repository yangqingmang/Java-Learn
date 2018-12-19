package com.qmyang.sort;

/**
 * 插入排序2
 * 逻辑：判断需要插入的数字如果小于前一个数字，则姜前一个数字后移
 * 然后需要插入的数字再和前面的数字对比，最后将需要插入的数字放到合适的位置
 */
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
