package binary;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by taalyko2 on 07/02/17.
 */
public class CounterGame {


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int to = Integer.valueOf(sc.nextLine());

        for (int i = 0; i < to; i++) {
            BigInteger n = new BigInteger(sc.nextLine().trim());
            String binaryString = n.toString(2);

            boolean who = false;
            while (!n.equals(BigInteger.ONE)) {
                if (!powerOfTwo(binaryString)) {
                    n = new BigInteger(getNextTwoPower(binaryString), 2);
                } else {
                    n = n.divide(new BigInteger("2"));
                }
                who = !who;
                binaryString = n.toString(2);
            }
            if (who) {
                System.out.println("Louise");
            } else {
                System.out.println("Richard");
            }

        }
    }


    public static String getNextTwoPower(String binary) {
        String res = binary.substring(2, binary.length());
        return "1" + res;

    }

    public static boolean powerOfTwo(String binary) {
        if (binary.length() == 1) {
            return false;
        }
        int counter = 0;
        for (int i = 0; i < binary.length(); i++) {
            char c = binary.charAt(i);
            if (c == '1') {
                counter++;
                if (counter > 1) {
                    return false;
                }
            }
        }
        return true;
    }
}
