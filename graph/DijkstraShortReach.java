package graph;


import javafx.util.Pair;
import lombok.ToString;

import java.util.*;


public class DijkstraShortReach {

    static class Edge {
        public Node x;
        public int distanceToMe;

        public Edge(Node x, int distanceToMe) {
            this.x = x;
            this.distanceToMe = distanceToMe;
        }

        @Override
        public boolean equals(Object obj) {
            Edge egdge = (Edge) obj;
            if (egdge.x.equals(this.x)) {
                return true;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.x, this.distanceToMe);
        }
    }


    static class Node {
        int id;
        Set<Node> children = new HashSet();

        public Node(int id) {
            this.id = id;
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

    public static class MyComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            Edge nodea = (Edge) o1;
            Edge nodeb = (Edge) o2;
            if (nodea.distanceToMe > nodeb.distanceToMe)
                return 1;
            else if (nodea.distanceToMe < nodeb.distanceToMe)
                return -1;
            else return 0;
        }
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int a0 = 0; a0 < t; a0++) {
            int n = in.nextInt();
            int m = in.nextInt();
            HashMap<Integer, Node> nodes = new HashMap();
            HashMap<Pair<Integer, Integer>, Integer> lenghts = new HashMap<>();
            for (int a1 = 0; a1 < m; a1++) {
                int x = in.nextInt();
                int y = in.nextInt();
                int r = in.nextInt();

                if (!nodes.containsKey(x))
                    nodes.put(x, new Node(x));
                if (!nodes.containsKey(y))
                    nodes.put(y, new Node(y));
                lenghts.put(new Pair<>(x, y), r);
                lenghts.put(new Pair<>(y, x), r);
                nodes.get(x).children.add(nodes.get(y));
                nodes.get(y).children.add(nodes.get(x));
            }
            int startIdx = in.nextInt();

            PriorityQueue<Edge> edgePriorityQueue = new PriorityQueue(new MyComparator());
            int[] distanceTo = new int[m + 1];
            for (int indexdistance = 0; indexdistance <= n; indexdistance++) {
                distanceTo[indexdistance] = Integer.MAX_VALUE;
            }
            distanceTo[startIdx] = 0;
            edgePriorityQueue.add(new Edge(nodes.get(startIdx), 0));
            while (!edgePriorityQueue.isEmpty()) {
                Edge nextMin = edgePriorityQueue.poll();

                for (Node child : nextMin.x.children) {
                    relaxEdge(nodes, nextMin, child, edgePriorityQueue, distanceTo, lenghts);
                }
            }

            for (int indexdistance = 1; indexdistance <= n; indexdistance++) {
                if (startIdx == indexdistance)
                    continue;
                if (distanceTo[indexdistance] == Integer.MAX_VALUE) {
                    distanceTo[indexdistance] = -1;
                }
                System.out.print(distanceTo[indexdistance] + " ");
            }
            System.out.println();

        }


    }


    public static void relaxEdge(HashMap<Integer, Node> nodes, Edge parent, Node child, PriorityQueue<Edge> edgePriorityQueue, int[] distanceTo, HashMap<Pair<Integer, Integer>, Integer> lengths) {
        if (distanceTo[child.id] > distanceTo[parent.x.id] + lengths.get(new Pair(child.id, parent.x.id))) {
            distanceTo[child.id] = distanceTo[parent.x.id] +
                    lengths.get(new Pair(child.id, parent.x.id));
            if (edgePriorityQueue.contains(new Edge(nodes.get(child.id), distanceTo[child.id]))) {
                edgePriorityQueue.remove(new Edge(nodes.get(child.id), distanceTo[child.id]));
                edgePriorityQueue.add(new Edge(nodes.get(child.id), distanceTo[child.id]));
            } else {
                edgePriorityQueue.add(new Edge(nodes.get(child.id), distanceTo[child.id]));
            }
        } else {
            return;
        }

    }


}
