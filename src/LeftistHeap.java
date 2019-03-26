public class LeftistHeap<E extends Comparable<E>> {

    private Node<E> root;

    public LeftistHeap() {
        root = null;
    }

    private static class Node<E extends Comparable> {
        E value;
        Node<E> left;
        Node<E> right;
        int nullPathLen;

        Node(E value) {
            this(value, null, null);
        }

        Node(E value, Node<E> left, Node<E> right) {
            this.value = value;
            this.left = left;
            this.right = right;
            this.nullPathLen = 0;
        }
    }

    public void insert(E value) {
        root = merge(new Node<>(value), root);
    }

    public void merge(LeftistHeap<E> tree) {
        if(this == tree) return;
        root = merge(root, tree.root);
        tree.makeEmpty();
    }

    private Node<E> merge(Node<E> node1, Node<E> node2) {
        if(node1 == null) return node2;
        if(node2 == null) return node1;
        return node1.value.compareTo(node2.value) > 0 ? merge1(node1, node2) : merge1(node2, node1);
    }

    private Node<E> merge1(Node<E> node1, Node<E> node2) {
        if(node1.left == null) node1.left = node2;
        else
        {
            node1.right = merge(node1.right, node2);
            if(node1.left.nullPathLen < node1.right.nullPathLen) {
                swapChildern(node1);
            }
            node1.nullPathLen = node1.right.nullPathLen + 1;
        }
        return node1;
    }

    private void swapChildern(Node<E> treeRoot) {
        Node<E> temp = treeRoot.right;
        treeRoot.right = treeRoot.left;
        treeRoot.left = temp;
    }

    public E deleteMax() {
        if(isEmpty()) return null;
        E maxValue = findMax();
        root = merge(root.left, root.right);
        return maxValue;
    }

    public E findMax() {
        return root.value;
    }

    /************************************************
     * Print the tree contents in sorted order.
     ***********************************************/
    public void printTree(String label) {
        System.out.println(label);
        if(isEmpty())
            System.out.println("Empty tree");
        else
            printTree(root,"");
    }

    /************************************************
     * Internal method to print a subtree in sorted order.
     * @param node the node that roots the tree.
     ***********************************************/
    private void printTree(Node<E> node, String indent) {
        if(node != null) {
            printTree(node.right, indent + "   ");
            System.out.println(indent + node.value + "("+ node.nullPathLen  +")");
            printTree(node.left, indent + "   ");
        }
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void makeEmpty() {
        root = null;
    }

    //Test Program
    public static void main(String[] args) {
        LeftistHeap<Integer> tree1 = new LeftistHeap<>();
        LeftistHeap<Integer> tree2 = new LeftistHeap<>();

        Integer valuesOfTree1[] = {12, 4, 33, 2, 424, 309, -109, 88, 55, 101, 0, -4, 29, -100, 123};
        Integer valuesOfTree2[] = {43, 203, -50, 300, 275, 505, 11, 21, 0};

        for(Integer item : valuesOfTree1) {
            tree1.insert(item);
        }

        for(Integer item : valuesOfTree2) {
            tree2.insert(item);
        }

        tree1.printTree("Tree 1:");
        System.out.println("\n---------------------------------\n");
        tree2.printTree("Tree 2");
        System.out.println("\n---------------------------------\n");

        tree1.merge(tree2);
        tree1.printTree("Merged trees:");
        System.out.println("\n---------------------------------\n");

        System.out.println("Max value " + tree1.deleteMax() + " was deleted from the merged tree\n");

        tree1.printTree("New merged tree");
        System.out.println("\n---------------------------------\n");
    }
}
