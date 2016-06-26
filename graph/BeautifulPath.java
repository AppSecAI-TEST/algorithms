package graph;

import javafx.util.Pair;
import lombok.ToString;

import java.util.*;


public class BeautifulPath {

    @ToString(exclude = "children")
    static class Node {
        int id;
        int distanceTo;
        Set<Node> children = new HashSet();

        public Node(int id) {
            this.id = id;
            this.distanceTo = Integer.MAX_VALUE;
        }

        @Override
        public boolean equals(Object obj) {
            Node node = (Node) obj;
            if (node.id == this.id)
                return true;
            else
                return false;
        }

        @Override
        public int hashCode() {
            return this.id;
        }
    }

    public static class MyComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            if (o1.distanceTo > o2.distanceTo)
                return 1;
            else if (o1.distanceTo < o2.distanceTo)
                return -1;
            else return 0;
        }
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        int t2 = in.nextInt();
        HashMap<Integer, Node> nodes = new HashMap();
        HashMap<Pair<Integer, Integer>, List<Integer>> lenghts = new HashMap<>();
        for (int a1 = 0; a1 < t2; a1++) {
            int x = in.nextInt();
            int y = in.nextInt();
            int r = in.nextInt();

            if (!nodes.containsKey(x))
                nodes.put(x, new Node(x));
            if (!nodes.containsKey(y))
                nodes.put(y, new Node(y));

            if (lenghts.containsKey(new Pair<>(x, y))) {
                lenghts.get(new Pair<>(x, y)).add(r);
            } else {
                lenghts.put((new Pair<>(x, y)), new LinkedList<>());
                lenghts.get(new Pair(x, y)).add(r);
            }

            if (lenghts.containsKey(new Pair<>(y, x))) {
                lenghts.get(new Pair<>(y, x)).add(r);
            } else {
                lenghts.put((new Pair<>(y, x)), new LinkedList<>());
                lenghts.get(new Pair(y, x)).add(r);
            }

            nodes.get(x).children.add(nodes.get(y));
            nodes.get(y).children.add(nodes.get(x));
        }
        int startIdx = in.nextInt();
        int endIdx = in.nextInt();

        PriorityQueue<Node> edgePriorityQueue = new PriorityQueue(new MyComparator());


        if (nodes.containsKey(startIdx))
            edgePriorityQueue.add(nodes.get(startIdx));
        else {
            System.out.println("-1");
            return;
        }
        nodes.get(startIdx).distanceTo = 0;
        while (!edgePriorityQueue.isEmpty()) {
            Node nextMin = edgePriorityQueue.poll();

            for (Node child : nextMin.children) {
                relaxEdge(nodes, nextMin, child, edgePriorityQueue, lenghts);
            }
        }

        for (Integer node : nodes.keySet()) {
            if (node == endIdx) {
                System.out.println(nodes.get(node).id);
            }

        }


    }


    public static void relaxEdge(HashMap<Integer, Node> nodes, Node parent, Node child, PriorityQueue<Node> edgePriorityQueue, HashMap<Pair<Integer, Integer>, List<Integer>> lengths) {

        for (int i = 0; i < lengths.get(new Pair(child.id, parent.id)).size(); i++) {
            if (child.distanceTo > (parent.distanceTo | lengths.get(new Pair(child.id, parent.id)).get(i))) {

                child.distanceTo = parent.distanceTo | lengths.get(new Pair(child.id, parent.id)).get(i);

                if (edgePriorityQueue.contains(nodes.get(child.id))) {
                    edgePriorityQueue.remove(nodes.get(child.id));
                    edgePriorityQueue.add(child);
                } else {
                    edgePriorityQueue.add(child);
                }
            } else {
                return;
            }
        }


    }
}
