package arrays;

import java.util.Map;
import java.util.Scanner;

/**
 * Created by taalyko2 on 26/03/17.
 */
public class CtciArrayLeftRotation {


    public static int[] arrayLeftRotation(int[] a, int n, int k) {
        String koko;

        int[] newArray = new int[n];
        for(int i=0;i<n;i++) {
            int newIndex;
            if((i-(k%n))>=0){
                newIndex = i-(k%n);
            }
            else {
                newIndex = n - (k % n) + i;
            }

            newArray[newIndex] = a[i];
        }
        return newArray;
    }

    public static void updateMap(Map<String,Integer> map, String str) {
        for(int i=0;i<str.length();i++) {
            if(map.containsKey(str.charAt(i))) {
                map.put(String.valueOf(str.charAt(i)),map.get(str.charAt(i)) + 1);
            }else {
                map.put(String.valueOf(str.charAt(i)),1);
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int a[] = new int[n];
        for(int a_i=0; a_i < n; a_i++){
            a[a_i] = in.nextInt();
        }

        int[] output = new int[n];
        output = arrayLeftRotation(a, n, k);
        for(int i = 0; i < n; i++)
            System.out.print(output[i] + " ");

        System.out.println();

    }
}
