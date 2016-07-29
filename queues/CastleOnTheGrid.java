package queues;

import lombok.ToString;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;


public class CastleOnTheGrid {

    @ToString
    static class Field {
        int i;
        int j;
        boolean visited;
        boolean available;
        public Field adjacent;
        public int movesFromStart = 0;

        public Field(int i, int j, boolean visited, boolean available) {
            this.i = i;
            this.j = j;
            this.visited = visited;
            this.available = available;
        }
    }

    static Field[][] map;
    static int size;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int to = Integer.valueOf(sc.nextLine());

        map = new Field[to][to];
        size = to;
        for (int i = 0; i < to; i++) {
            String line = sc.nextLine();
            for (int j = 0; j < to; j++) {
                if (line.charAt(j) == 'X') {
                    map[i][j] = new Field(i, j, false, false);
                } else {
                    map[i][j] = new Field(i, j, false, true);
                }
            }
        }
        String[] positions = sc.nextLine().split("\\s");
        int starti = Integer.valueOf(positions[0]);
        int startj = Integer.valueOf(positions[1]);
        int endi = Integer.valueOf(positions[2]);
        int endj = Integer.valueOf(positions[3]);


        travers(starti, startj, map, endi, endj);
    }

    public static void travers(int starti, int startj, Field[][] map, int endi, int endj) {
        map[starti][startj].visited = true;
        Stack<Field> toSee = new Stack();

        toSee.add(map[starti][startj]);

        while (!toSee.isEmpty()) {
            Field current = toSee.pop();
            if (current.adjacent != null && current.adjacent.adjacent != null && current.i == current.adjacent.i && current.i == current.adjacent.adjacent.i) {
                current.movesFromStart = current.adjacent.movesFromStart;
            } else if (current.adjacent != null && current.adjacent.adjacent != null && current.j == current.adjacent.j && current.j == current.adjacent.adjacent.j) {
                current.movesFromStart = current.adjacent.movesFromStart;
            } else {
                if (current.adjacent != null && current.adjacent.adjacent == null) {
                    current.movesFromStart = current.adjacent.movesFromStart;
                } else if (current.adjacent != null)
                    current.movesFromStart = current.adjacent.movesFromStart + 1;
                else
                    current.movesFromStart++;
            }
            if (current.available)
                System.out.println("" + current.movesFromStart + " row:" + current.i + " col:" + current.j + ")");

            current.visited = true;
            if (checkIfEnd(current, endi, endj)) {
                //
            }
            List<Field> moves = getPossibleMoves(current);
            for (Field move : moves) {
                move.adjacent = current;
                toSee.add(move);
            }

        }

        Field previous = map[endi][endj];
        int move = 0;
    }


    public static boolean isBegining(Field field, int starti, int starty) {
        if (field.i == starti && field.j == starty) {
            return true;
        }
        return false;
    }

    public static boolean checkIfEnd(Field end, int endi, int endj) {
        if (end.i == endi && end.j == endj) {
            return true;
        }
        return false;
    }


    public static List<Field> getPossibleMoves(Field current) {
        List<Field> results = new LinkedList<Field>();
        if (current.i > 0 && map[current.i - 1][current.j].available && !map[current.i - 1][current.j].visited) {//up
            results.add(map[current.i - 1][current.j]);
        }
        if ((current.i + 1) < size && map[current.i + 1][current.j].available && !map[current.i + 1][current.j].visited) {//down
            results.add(map[current.i + 1][current.j]);
        }
        if (current.j > 0 && map[current.i][current.j - 1].available && !map[current.i][current.j - 1].visited) {
            results.add(map[current.i][current.j - 1]);
        }
        if ((current.j + 1) < size && map[current.i][current.j + 1].available && !map[current.i][current.j + 1].visited) {
            results.add(map[current.i][current.j + 1]);
        }
        return results;
    }
}
