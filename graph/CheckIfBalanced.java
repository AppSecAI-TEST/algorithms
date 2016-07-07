package graph;

import lombok.ToString;

import java.util.Deque;


public class CheckIfBalanced {

    @ToString
    static class Node {
        int value;
        Node parent;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }


    }


    public static Node findsucessorOfTheNode(Node current, Node node) {
        if (current == null) {
            return null;
        }
        if (current.equals(node) && current.parent != null && current.parent.left.equals(current)) {
            return current.parent;
        }
        if (current.equals(node) && current.parent != null && current.parent.parent != null && current.parent.right.equals(current)) {
            return current.parent.parent;
        }


        Node returned = findsucessorOfTheNode(current.left, node);
        Node returned2 = findsucessorOfTheNode(current.right, node);

        if (returned != null)
            return returned;
        return returned2;
    }

    public static boolean checkTree(Node node, int min, int max) {
        if (node == null) {
            return true;
        }
        if (node.value >= min && node.value <= max) {
            return checkTree(node.left, min, node.value) && checkTree(node.right, max, node.value);
        } else
            return false;
    }

    public static int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        int leftHgt = getHeight(node.left);
        int rightHgt = getHeight(node.right);
        System.out.println(node.value + " on difference between" + leftHgt + " and " + rightHgt + "--" + (Math.abs(leftHgt) - Math.abs(rightHgt)));
        System.out.println(node.value + " on difference between" + leftHgt + " and " + rightHgt + "--" + (Math.abs(leftHgt) - Math.abs(rightHgt)));
        if (Math.abs(Math.abs(leftHgt) - Math.abs(rightHgt)) > 1) {
            throw new RuntimeException("Bad tree");
        } else {
            return Math.max(leftHgt, rightHgt) + 1;
        }

    }
}
