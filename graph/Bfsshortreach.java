package graph;

import java.util.*;


public class Bfsshortreach {

    static HashMap<Integer, Node> nodeSet = new HashMap<>();

    static int[][] adjacent;
    static int[] distTo;

    public static void traverse(Node node) {

        for (int v = 0; v < distTo.length; v++)
            distTo[v] = Integer.MAX_VALUE;

        Node start = node;
        Queue<Integer> stack = new ArrayDeque();

        stack.add(node.id);
        distTo[node.id] = 0;

        while (!stack.isEmpty()) {
            node = nodeSet.get(stack.poll());
            node.visited = true;
            for (Integer elementint : node.successors) {
                Node element = nodeSet.get(elementint);
                if (!element.visited) {
                    distTo[elementint] = distTo[node.id] + 6;
                    adjacent[element.id][node.id] = getLastMinLength(node) + 6;
                    stack.add(element.id);
                    element.visited = true;
                }
            }
        }

        for (int i = 1; i < distTo.length; i++) {
            if (i != start.id) {
                if (distTo[i] == Integer.MAX_VALUE) {
                    System.out.print("-1 ");
                    continue;
                }
                System.out.print(distTo[i] + " ");
            }

        }
        System.out.println();
    }

    public static int getLastMinLength(Node node) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < adjacent[node.id].length; i++) {
            if (adjacent[node.id][i] != 0 && adjacent[node.id][i] < min) {
                min = adjacent[node.id][i];
                return min;
            }
        }

        return min == Integer.MAX_VALUE ? 0 : min;
    }

    private static void printTask(Node start) {
        for (int i = 1; i < adjacent.length; i++) {
            if (i == start.id) continue;
            int min = Integer.MAX_VALUE;
            for (int j = 1; j < adjacent[i].length; j++) {
                if (adjacent[i][j] != 0 && adjacent[i][j] < min) {
                    min = adjacent[i][j];
                }
            }
            if (min == Integer.MAX_VALUE) {
                System.out.print(-1 + " ");
            } else {
                System.out.print(min + " ");
            }
        }
        System.out.println();
    }


    private static void print() {
        System.out.print("  ");
        for (int i = 1; i < adjacent.length; i++) {
            System.out.print(i + "    ");
        }
        for (int i = 1; i < adjacent.length; i++) {
            System.out.println();
            System.out.print(i + ":");
            for (int j = 1; j < adjacent[i].length; j++) {
                System.out.print(adjacent[i][j] + "    ");
            }
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int q = Integer.valueOf(sc.nextLine());
        for (int iii = 0; iii < q; iii++) {
            int n = sc.nextInt();
            int m = sc.nextInt();

            for (int j = 0; j < m; j++) {
                Node u = new Node(sc.nextInt());
                Node v = new Node(sc.nextInt());

                if (nodeSet.containsKey(u.id)) {
                    nodeSet.get(u.id).successors.add(v.id);
                } else {
                    nodeSet.put(u.id, u);
                    nodeSet.get(u.id).successors.add(v.id);
                }

                if (nodeSet.containsKey(v.id)) {
                    nodeSet.get(v.id).successors.add(u.id);
                } else {
                    nodeSet.put(v.id, v);
                    v.successors.add(u.id);
                }

            }
            adjacent = new int[n + 1][n + 1];
            distTo = new int[n + 1];
            int element = sc.nextInt();
            if (nodeSet.get(element) == null) {
                printTask(new Node(element));
                nodeSet.clear();

                continue;
            }
            traverse(nodeSet.get(element));

            nodeSet.clear();
        }
    }


}

class Node {
    public boolean visited;
    public int id;
    public Set<Integer> successors
            = new TreeSet<>();

    public Node(int id) {
        this.id = id;
    }


}




