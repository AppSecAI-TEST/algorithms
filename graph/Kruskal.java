package graph;

import lombok.ToString;

import java.util.*;

public class Kruskal {


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        List<Edge> sortedEdges = new LinkedList();
        int n = Integer.valueOf(sc.nextInt());
        int m = Integer.valueOf(sc.nextInt());
        List<Integer>[] succs = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) {
            succs[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int len = sc.nextInt();
            sortedEdges.add(new Edge(x, y, len));
        }
        sortedEdges.sort(new GraphComparator());


        Set<Edge> mstEdges = new LinkedHashSet<>();
        for (Edge ed : sortedEdges) {
            if (isConnected(ed.x, ed.y, succs)) {
                continue;
            }
            mstEdges.add(ed);
            succs[ed.x].add(ed.y);
            succs[ed.y].add(ed.x);
        }
        int sum = 0;
        for (Edge ed : mstEdges) {
            sum += ed.length;

        }
        System.out.println(sum);

    }

    public static boolean isConnected(int root, int y, List<Integer>[] succs) {
        if (root == y) return true;
        boolean[] visited = new boolean[succs.length];
        Stack<Integer> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            int current = stack.pop();
            if (current == y) return true;
            visited[current] = true;
            for (int child : succs[current]) {
                if (!visited[child]) {
                    stack.add(child);
                }
            }
        }

        return false;
    }

    public static boolean hastheSameRoot(Edge newEdge, List<Integer>[] roots) {
        List<Integer> a = findRoot(newEdge.y, roots);
        List<Integer> b = findRoot(newEdge.x, roots);
        for (Integer elem : a) {
            if (b.contains(elem)) {
                return true;
            }
        }
        return false;
    }

    public static List<Integer> findRoot(int node, List<Integer>[] roots) {
        if (roots[node].size() == 0) {
            return Collections.emptyList();
        }
        if (roots[node].size() == 1) {
            return new LinkedList<Integer>() {{
                add(roots[node].get(0));
            }};
        }
        List<Integer> rootsForNode = new LinkedList<>();
        for (Integer root : roots[node]) {
            rootsForNode.addAll(findRoot(root, roots));
        }
        return rootsForNode;
    }
}

class GraphComparator implements Comparator<Edge> {


    @Override
    public int compare(Edge o1, Edge o2) {
        if (o1.length < o2.length)
            return -1;
        else if (o1.length > o2.length)
            return 1;
        return 0;
    }
}


@ToString
class Edge {
    public int x;
    public int y;
    public long length;

    public Edge(int x, int y, long length) {
        this.x = x;
        this.y = y;
        this.length = length;

    }
}