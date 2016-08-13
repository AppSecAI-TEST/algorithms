package sorting;

/**
 * Created by taalyko2 on 25/03/17.
 */
public class MergeSort2 {

    private static int counter = 0;


    public static void main(String[] args) {
        int[] arr = new int[9];
        arr[0] = 11;
        arr[1] = 15;
        arr[2] = 1;
        arr[3] = 4;
        arr[4] = 5;
        arr[5] = 6;
        arr[6] = 7;
        arr[7] = 12;
        arr[8] = 13;

        int[] sorted = mergesort(arr, 0, arr.length - 1);
        for (int i : sorted) {
            System.out.println(i);
        }
        System.out.println(counter);

    }

    public static int[] mergesort(int[] arr, int start, int end) {
        if (end - start == 0) {
            int[] result = new int[1];
            result[0] = arr[start];
            return result;
        }
        if (end - start == 1) {
            int[] result = new int[2];
            if (arr[start] <= arr[end]) {
                result[0] = arr[start];
                result[1] = arr[end];

            } else {
                result[0] = arr[end];
                result[1] = arr[start];
                counter++;
            }
            return result;
        }

        int middle = (end - start) / 2 + start;
        int[] arr3 = mergesort(arr, start, middle);
        int[] arr4 = mergesort(arr, middle + 1, end);
        int[] result = sortTwoSortedArrays(arr3, arr4);
        return result;
    }

    public static int[] sortTwoSortedArrays(int[] arr, int[] arr2) {
        int[] result = new int[arr.length + arr2.length];
        int a = 0;
        int b = 0;
        for (int i = 0; i < arr.length + arr2.length; i++) {

            if (a == arr.length) {

                result[i] = arr2[b];
                b++;
                continue;
            }
            if (b == arr2.length) {
                //counter++;
                result[i] = arr[a];
                a++;
                continue;
            }
            int element;
            if (arr[a] <= arr2[b]) {
                element = arr[a];
                a++;
            } else {
                counter++;
                element = arr2[b];
                b++;
            }
            result[i] = element;
        }
        return result;
    }
}
