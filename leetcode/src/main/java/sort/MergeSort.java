package sort;

import org.w3c.dom.ls.LSOutput;

import java.util.Arrays;
import java.util.List;

public class MergeSort {
    private static void swap(int arr[], int i, int j) {
        if (arr == null || i == j || i < 0 || j <0 || j> arr.length-1 || i>arr.length-1) {
            return;
        }
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        return;
    }
    public static void bubbleSort(int arr[]) {
        for(int i=1; i < arr.length; i++) {
            int index = i;
            for (int j=i-1; j >= 0 && arr[j] > arr[j+1]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }

    public static void mergeSort(int arr[]) {
        
    }

    public static void main(String[] args) {
        System.out.println("hello");
        int arr[] = {2,5,8,3,1,0,-10,-8};
        bubbleSort(arr);

        System.out.println("args = " + Arrays.toString(arr));
    }

}
