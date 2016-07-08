package graph;

import java.util.*;


public class EvenTree {

    static class Node {
        int id;
        Node parent;

        public Node(int id) {
            this.id = id;
        }

        HashMap<Integer, Node> children = new HashMap();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        HashMap<Integer, Node> nodes = new HashMap<>();
        int n = Integer.valueOf(sc.nextInt());
        int m = Integer.valueOf(sc.nextInt());

        for (int i = 0; i < m; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            Node n1 = new Node(x);
            Node n2 = new Node(y);
            if (nodes.containsKey(y)) {
                nodes.get(y).children.put(x, n1);
            } else {
                nodes.put(y, n2);
                nodes.get(y).children.put(x, n1);
            }
            if (!nodes.containsKey(x))
                nodes.put(x, n1);
        }
        traverseDfs(nodes.get(1));
        System.out.println(counter);
    }

    public static int counter = 0;

    public static int traverseDfs(Node node) {
        if (node.children.values().isEmpty()) {
            return 1;
        }
        int sum = 1;
        for (Node n : node.children.values()) {
            sum = sum + traverseDfs(n);
        }
        if (sum % 2 == 0 && node.id != 1) {
            counter++;
        }
        return sum;
    }
}
