package dynamic;

import java.util.HashSet;
import java.util.Scanner;


public class SamandSubstrings {


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String t = sc.nextLine();

        System.out.println(combinations("", t, t));


    }

    public static HashSet<String> visited = new HashSet<>();

    public static int combinations(String prefix, String rest, String original) {
        int value = 0;
        if (prefix != "") {
            System.out.println(prefix);
            value = Integer.valueOf(prefix);
        }
        if (!original.contains(prefix)) {
            return 0;
        }

        for (int i = 0; i < rest.length(); i++) {
            value = value + combinations(prefix + rest.charAt(i), rest.substring(i + 1, rest.length()), original);
        }
        return value;
    }

    public static void permutation(String prefix, String str) {
        //System.out.println(prefix + " vs " + str);
        if (str.length() == 0) {
            System.out.println(prefix);
            return;
        }


        for (int i = 0; i < str.length(); i++) {
            permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, str.length()));
        }
    }
}
