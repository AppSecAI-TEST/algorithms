package dynamic;

import java.util.Scanner;


public class Abbr {


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int t = Integer.valueOf(sc.nextLine());


        for (int i = 0; i < t; i++) {
            String a = sc.nextLine();
            String b = sc.nextLine();

            if (tryToMatch(a, b, 0, 0)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }

    }

    public static boolean tryToMatch(String a, String b, int aIdx, int bIdx) {
        while (aIdx < a.length() && bIdx < b.length()) {
            if (a.charAt(aIdx) == b.charAt(bIdx)) {
                aIdx++;
                bIdx++;
                continue;
            }
            if (Character.toUpperCase(a.charAt(aIdx)) == b.charAt(bIdx)) {
                specialCase(a, b, aIdx, bIdx);
                aIdx++;
                bIdx++;
                continue;
            }
            if (Character.isLowerCase(a.charAt(aIdx))) {
                a = a.substring(0, aIdx) + a.substring(aIdx + 1, a.length());
            } else {
                return false;
            }
        }
        int diff = a.length() - b.length();
        if (diff == 0) {
            return true;
        }
        if (diff > 0) {
            for (int i = a.length() - diff; i < a.length(); i++) {
                if (Character.isLowerCase(a.charAt(aIdx))) {
                    a = a.substring(0, aIdx) + a.substring(aIdx + 1, a.length());
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    public static boolean specialCase(String a, String b, int aIdx, int bIdx) {

        return false;
    }
}
