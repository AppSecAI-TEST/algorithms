package sorting;

import java.util.Scanner;

/**
 * Created by taalyko2 on 09/03/17.
 */
public class MergeSort {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] numbers = sc.nextLine().split(" ");
        int[] arr = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            arr[i] = Integer.valueOf(numbers[i]);
        }
        arr = mergeSort(arr);
        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + " ");
    }

    public static int[] mergeSort(int[] arr) {
        if (arr.length == 1) {
            return arr;
        }
        if (arr.length == 2) {
            if (arr[0] > arr[1]) {
                int tmp = arr[0];
                arr[0] = arr[1];
                arr[1] = tmp;
            }
            return arr;
        }

        int[] arrPart0 = mergeSort(getFrstHalf(arr));
        int[] arrPart1 = mergeSort(getSecondHalf((arr)));
        return merge(arrPart0, arrPart1);
    }

    public static int[] getFrstHalf(int[] arr) {
        int l = arr.length / 2;
        int[] result = new int[l];
        for (int i = 0; i < l; i++) {
            result[i] = arr[i];
        }
        return result;
    }

    public static int[] getSecondHalf(int[] arr) {
        int l = arr.length / 2;
        int[] result = new int[arr.length - l];
        int j = 0;
        for (int i = l; i < arr.length; i++) {
            result[j] = arr[i];
            j++;
        }
        return result;
    }


    static int[] merge(int[] a, int[] b) {
        int[] c = new int[a.length + b.length];
        int i = 0, j = 0;
        for (int k = 0; k < c.length; k++) {
            if (i >= a.length) c[k] = b[j++];
            else if (j >= b.length) c[k] = a[i++];
            else if (a[i] <= b[j]) c[k] = a[i++];
            else c[k] = b[j++];
        }
        return c;
    }
}
