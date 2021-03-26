package com.szu.practice.l01_sort;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/3/10 21:04
 */

public class MergeSort {

    public static void main(String[] args) {

        int[] arr = {4,5,9,10,8,9,4};
        mergeSort(arr, 0,  arr.length-1);
        for (int i:arr) {
            System.out.println(i);
        }
    }

    private static void mergeSort(int[] arr, int low, int high) {
        int mid = low + ( (high-low) >> 1 );
        if (low < high){
            mergeSort(arr, low,mid);
            mergeSort(arr, mid+1,  high);
            merge(arr, low, mid, high);
        }


    }

    private static void merge(int[] arr, int low ,int mid, int high) {

        int i = low;
        int j = mid + 1;
        int[] help = new int[high-low+1];
        int index = 0;
        while( i <= mid && j <=high  ){
            if (arr[i] < arr[j])
                help[index++] = arr[i++];
            else
                help[index++] = arr[j++];
        }
        while(i <= mid)
            help[index++] = arr[i++];

        while(j <= high)
            help[index++] = arr[j++];

        for (int k = 0; k < help.length; k++) {
            arr[k+low]=help[k];
        }

    }

}
