package dynamic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Equal {

    public static void main(String[] args) {
        int[] A = new int[6];
        A[0] = 1;
        A[1] = -2;
        A[2] = 0;
        A[3] = 9;
        A[4] = -1;
        A[5] = -2;

        System.out.println(solution(A));
    }

    public static int solution(int[] A) {
        int n = A.length;
        int[] dp = new int[n];
        dp[0] = A[0];
        for (int i = 1; i < n; i++) {
            int max = dp[i - 1] + A[i];
            for (int j = 1; j <= 6; j++) {
                if (i - j >= 0) {
                    max = Math.max(dp[i - j] + A[i], max);
                }
            }
            dp[i] = max;
        }
        return dp[n - 1];
    }

    public static int getMovesCount(ArrayList<Integer> people, int[] chockolates, int moves) {
        boolean isSame = false;
        for (int i = 1; i < people.size(); i++) {
            if (people.get(i) != people.get(i - 1)) {
                isSame = false;
                break;
            } else {
                isSame = true;
            }

        }
        if (isSame) return moves;
        int diff = people.get(people.size() - 1) - people.get(0);
        if (diff >= 5) {
            if (tryAdd(5, people.size() - 1, (List) people.clone()) < diff) {
                tryAdd(5, people.size() - 1, people);
                moves = getMovesCount(people, chockolates, moves + 1);
                return moves;
            }
        }
        if (diff >= 2) {
            if (tryAdd(2, people.size() - 1, (List) people.clone()) < diff) {
                tryAdd(2, people.size() - 1, people);
                moves = getMovesCount(people, chockolates, moves + 1);
                return moves;
            }
        }
        if (diff >= 1) {
            if (tryAdd(1, people.size() - 1, (List) people.clone()) < diff) {
                tryAdd(1, people.size() - 1, people);
                moves = getMovesCount(people, chockolates, moves + 1);
                return moves;
            }
        }
        return moves;
    }

    public static int tryAdd(int many, int apart, List<Integer> people) {
        for (int i = 0; i < apart; i++) {
            int cach = people.get(i);
            people.remove(i);
            people.add(i, cach + many);
        }
        for (int i = apart + 1; i < people.size(); i++) {
            int cach = people.get(i);
            people.remove(i);
            people.add(i, cach + many);
        }
        Collections.sort(people);
        return people.get(people.size() - 1) - people.get(0);
    }
}
