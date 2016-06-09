package dynamic;

import java.util.Arrays;
import java.util.Deque;


public class MinAbsSum {

    public static void main(String[] args) {
        int[] A = new int[6];
        A[0] = 1;
        A[1] = 500;
        A[2] = 2;
        A[3] = -2;
        A[4] = -2;
        A[5] = -2;
        System.out.println(solution(A));
    }

    public static int dp(int[] A) {
        return 0;
    }

    public static int solution(int[] A) {
        Deque po;
        int[] preSum = new int[A.length + 1];

        for (int i = 1; i < preSum.length; i++) {
            preSum[i] += preSum[i - 1] + A[i - 1];
        }

        Arrays.sort(preSum);
        int min = 2 * 10000 + 1;

        for (int i = 1; i < preSum.length; i++) {
            int d = preSum[i] - preSum[i - 1];
            if (d < min) {
                min = d;
            }
        }

        return min;
    }
}
