package maiboroda.com.labs_course_2.lab2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class BinaryTree<T extends Comparable<T>> implements Iterable<T> {
    class Node {
        T data;
        Node left, right;

        Node(T data) {
            this.data = data;
        }
    }

    private Node root;

    public void insert(T value) {
        root = insertRec(root, value);
    }

    private Node insertRec(Node node, T value) {
        if (node == null) return new Node(value);
        if (value.compareTo(node.data) < 0)
            node.left = insertRec(node.left, value);
        else
            node.right = insertRec(node.right, value);
        return node;
    }

    private void postorder(Node node, List<T> result) {
        if (node != null) {
            postorder(node.left, result);
            postorder(node.right, result);
            result.add(node.data);
        }
    }

    @Override
    public Iterator<T> iterator() {
        List<T> result = new ArrayList<>();
        postorder(root, result);
        return result.iterator();
    }
}