package com.szu;/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/3/12 13:19
 */

public class Test {

    public static void main(String[] args) {
        int[] arr = {2, 1, -1, 5, 4, 6, 8, 4, 6, 8, 9};

        mergeSort(arr, 0, arr.length - 1);
        for (int i : arr) {
            System.out.print(i + " -> ");
        }
        System.out.println();
        int[] arr2 = {2, 1, -1, 5, 4, 6, 8, 4, 6, 8, 9};
        quickSort(arr2, 0, arr2.length - 1);
        for (int i : arr2) {
            System.out.print(i + " -> ");
        }
        int[] arr3 = {2, 1, -1, 5, 4, 6, 8, 4, 6, 8, 9};
        heapSort(arr3);
        heapInsert(arr3, 7);
        for (int i : arr3) {
            System.out.print(heapPop(arr3));
        }
    }

    private static int heapPop(int[] arr3) {
        int ans = arr3[0];
        swap(arr3, 0, arr3.length-1);
        slipDown(arr3, 0);
        return ans;
    }

    private static void slipDown(int[] arr3, int index) {
        int leftSon = (index*2)+1;

        while (leftSon < arr3.length){

            int smallestSon = (leftSon+1) >= arr3.length ? leftSon : arr3[leftSon] < arr3[leftSon+1] ? leftSon : leftSon+1;
            swap(arr3, smallestSon, index);
            index = smallestSon;
            leftSon = (index*2)+1;
        }

    }

    private static void heapInsert(int[] arr3, int val) {
        int[] newArr = new int[arr3.length+1];
        System.arraycopy(arr3, 0, newArr, 0, arr3.length);
        newArr[arr3.length] = val;
        heapify(newArr);
    }

    private static void heapify(int[] arr3) {
        int length = arr3.length-1;
        while (arr3[length] > arr3[(length-1)/2]){
            int index = length;
            int father = (length-1)/2;
            while (arr3[index] < arr3[father]){
                swap(arr3, father, index);
                index = father;
                father = (index - 1) / 2;
            }

        }

    }

    private static void heapSort(int[] arr3) {
        for (int i = arr3.length - 1; i >= 0; i--) {
            while (arr3[i] > arr3[(i-1)/2]){
                int curIndex = i;
                int father = (curIndex - 1) / 2;
                while (arr3[father] < arr3[curIndex]) {
                    swap(arr3, father, curIndex);
                    curIndex = father;
                    father = (curIndex - 1) / 2;
                }
            }
        }
    }




    private static void swap(int[] arr3, int i, int j) {
        int temp = arr3[i];
        arr3[i] = arr3[j];
        arr3[j] = temp;
    }

    private static void mergeSort(int[] arr, int low, int high) {

        if (low < high) {

            int mid = low + (high - low) / 2;
            mergeSort(arr, low, mid);
            mergeSort(arr, mid + 1, high);
            merge(arr, low, mid, high);
        }

    }

    private static void merge(int[] arr, int low, int mid, int high) {
        int i = low, j = mid + 1;
        int help[] = new int[high - low + 1];
        int helpIndex = 0;
        while (i <= mid && j <= high) {

            if (arr[i] <= arr[j])
                help[helpIndex++] = arr[i++];

            else
                help[helpIndex++] = arr[j++];

        }

        while (i <= mid)
            help[helpIndex++] = arr[i++];

        while (j <= high)
            help[helpIndex++] = arr[j++];

        for (int k = 0; k < help.length; k++) {
            arr[low + k] = help[k];
        }
    }


    private static void quickSort(int[] arr2, int low, int high) {
        if (low < high) {
            int index = findIndex(arr2, low, high);
            quickSort(arr2, low, index - 1);
            quickSort(arr2, index + 1, high);
        }
    }

    private static int findIndex(int[] arr2, int low, int high) {
        int tem = arr2[low];
        while (low < high) {

            while (low < high && arr2[high] >= tem) high--;
            arr2[low] = arr2[high];
            while (low < high && arr2[low] < tem) low++;
            arr2[high] = arr2[low];

        }
        arr2[low] = tem;
        return low;
    }

}