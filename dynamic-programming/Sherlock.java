package dynamic;

import java.util.ArrayList;
import java.util.Scanner;


public class Sherlock {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int t = Integer.valueOf(sc.nextLine());

        for (int tt = 0; tt < t; tt++) {
            int n = Integer.valueOf(sc.nextLine());
            ArrayList<Integer> numbers = new ArrayList();
            for (String ii : sc.nextLine().split(" ")) {
                numbers.add(Integer.valueOf(ii));
            }
            ArrayList<Integer> anumbers = new ArrayList();
            int val;
            for (int i = 0; i < numbers.size(); i++) {

                if (i % 2 == 0) {
                    val = numbers.get(i);
                } else {
                    val = 1;
                }
                anumbers.add(val);
            }
            int sum = 0;
            for (int i = 1; i < anumbers.size(); i++) {
                sum += Math.abs(anumbers.get(i - 1) - anumbers.get(i));
            }
            System.out.println(sum);
        }
    }
}
