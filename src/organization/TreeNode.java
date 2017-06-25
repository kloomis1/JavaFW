package organization;

/*
*Created by: Chris Dovolis
*Maintained by: Kyle Loomis
*Details: csci 1933 - lab 004
*/

// This is a class for Binary Search Tree nodes
public class TreeNode<E extends Comparable<E>> {

    // Member Data
    private E data;
    private TreeNode<E> leftChild;
    private TreeNode<E> rightChild;

    // Constructors
    public TreeNode(E data, TreeNode<E> leftChild, TreeNode<E> rightChild) {
        this.data = data;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public TreeNode() {
        this(null, null, null);
    }
    public TreeNode(E data) {
        this(data, null, null);
    }
    public TreeNode(E data, TreeNode<E> leftChild) {
        this(data, leftChild, null);
    }

    // Getters and setters
    public E getData() {
        return this.data;
    }
    public TreeNode<E> getLeftChild() {
        return this.leftChild;
    }
    public TreeNode<E> getRightChild() {
        return this.rightChild;
    }
    public void setData(E data) {
        this.data = data;
    }
    public void setLeftChild(TreeNode<E> leftChild) {
        this.leftChild = leftChild;
    }
    public void setRightChild(TreeNode<E> rightChild) {
        this.rightChild = rightChild;
    }

    public static int treeLevels(TreeNode<Integer> root) {
        if (root == null) return 0;
        else {
            // calculates left and right levels
            int l = treeLevels(root.getLeftChild());
            int r = treeLevels(root.getRightChild());
            if (l > r) return l + 1;
            else return r + 1;
        }
    }

    public static int countLeaves(TreeNode t) {
        if (t == null)
            return 0;
        else {
            if (t.getRightChild() == null && t.getLeftChild() == null) return 1;
            int r = countLeaves(t.getRightChild());
            int l = countLeaves(t.getLeftChild());
            return r + l;
        }
    }

    public static boolean lookup(int n, TreeNode t) {
        TreeNode current = t;

        while (current != null) {
            if ((int) current.getData() == n)
                return  true;
            else if (n > (int) current.getData())
                current = current.getRightChild();
            else current = current.getLeftChild();
        }

        return false;
    }
}