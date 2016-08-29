package tree;

import lombok.*;

public class BalancedTree {


    public static void main(String[] args) {
        Node two = Node.builder().val(2).ht(1).build();
        Node five = Node.builder().val(5).ht(1).build();
        Node four = Node.builder().val(4).ht(2).right(five).build();
        Node three = Node.builder().val(3).ht(3).right(four).build();
        three.setLeft(two);
        insert(three, 6);
    }

    private static int setHeight(Node root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max((root.left != null ? root.left.ht : 0), (root.right != null ? root.right.ht : 0));
    }


    static Node insert(Node root, int val) {
        if (root == null) {
            return Node.builder().ht(1).val(val).build();
        }
        if (root.val > val) {
            root.left = (insert(root.left, val));
        } else {
            root.right = (insert(root.right, val));
        }
        int factor = getFactor(root);
        if (factor > 1) {
            Node middle = root.left;
            int factorMid = getFactor(middle);
            if (factorMid < 0) { //left right case
                root.left = rotateLeft(root.left);
                root = rotateRight(root);
            } else //left left case
            {
                root = rotateRight(root);
            }
        } else if (factor < -1) {
            Node middle = root.right;
            int factorMid = getFactor(middle);
            if (factorMid < 0) {  //right right case
                root = rotateLeft(root);
            } else //right left case
            {
                root.right = (rotateRight(root.right));
                root = rotateLeft(root);
            }
        }

        root.ht = (1 + Math.max((root.left != null ? root.left.ht : 0),
                (root.right != null ? root.right.ht : 0)));

        return root;
    }

    private static Node rotateLeft(Node root) {
        Node newRoot = root.right;
        root.right = root.right.left;
        newRoot.left = root;
        root.ht = setHeight(root);

        newRoot.ht = setHeight(newRoot);

        return newRoot;
    }

    private static Node rotateRight(Node root) {
        Node newRoot = root.left;
        root.left = root.left.right;
        newRoot.right = root;
        root.ht = setHeight(root);
        newRoot.ht = setHeight(newRoot);

        return newRoot;
    }


    private static int getFactor(Node root) {
        int left = 0;
        int right = 0;
        if (root.left != null) {
            left = root.left.ht;
        }
        if (root.right != null) {
            right = root.right.ht;
        }
        return left - right;
    }


}

@ToString
@Getter
@Setter
@Builder
class Node {
    int val;   //Value
    int ht = 1;      //Height
    Node left;   //Left child
    Node right;   //Right child
}
