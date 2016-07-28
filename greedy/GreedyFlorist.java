package greedy;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class GreedyFlorist {

    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        int K = Integer.parseInt(in.readLine());
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < N; i++)
            list.add(Integer.parseInt(in.readLine()));

        int unfairness = Integer.MAX_VALUE;

        Collections.sort(list);

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < list.size(); i++) {
            if (i + K > list.size()) {
                break;
            }
            if (min > getUnfairness(i, i + K, list)) {
                min = getUnfairness(i, i + K, list);
            }
        }
        unfairness = min;
        System.out.println(unfairness);
    }

    public static int getUnfairness(int begin, int end, List<Integer> list) {
        return list.get(end - 1) - list.get(begin);
    }
}