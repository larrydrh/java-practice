package dui;

public class Solution {

    private void swim(int index, int[] arr) {
        while (index>0 && arr[index] > arr[(index-1)/2]) {
            swap(arr, index, (index-1)/2);
        }
    }
    private void sink(int index, int[] arr) {
        while(index*2+1 < arr.length ) {
            if (index*2+2 < arr.length) {
                if (arr[index] < arr[index*2+2] || arr[index] < arr[index*2 + 1]) {
                    if (arr[index*2+2] < arr[index*2+1]) {
                        swap(arr, index, index*2+1);
                        index = index*2+1;
                    } else {
                        swap(arr, index, index*2+2);
                        index = index*2+2;
                    }
                } else {
                    break;
                }
            } else if (index*2+1 < arr.length) {
                if (arr[index*2+1] > arr[index]) {
                    swap(arr, index, index*2+1);
                    index = index*2+1;
                }  else {
                    break;
                }
            }
        }
    }
    private void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    private void formDui(int[] arr) {
        for (int i = arr.length-1; i >=0; i--) {
            swim(i, arr);
            sink(i, arr);
        }
    }

    public static void main(String[] args) {
        int[] a = {1,2,3,4,5,6};
        Solution s = new Solution();
        s.formDui(a);
    }
}
