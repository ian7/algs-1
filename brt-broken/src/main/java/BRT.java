public class BRT<Key extends Comparable<Key>, Value> {
    Node<Key, Value> root;
    int size;

    BRT() {
        this.root = null;
    }

    private class Node<Key, Value> {
        private final Key k;
        private Value v;
        private Node<Key, Value> left;
        private Node<Key, Value> right;

        public Node(Key k, Value v) {
            this.k = k;
            this.v = v;
            this.left = null;
            this.right = null;
        }
    }

    public Node<Key, Value> set(Key k, Value v) {
        this.root = set(this.root, k, v);
        return this.root;
    }

    public Node<Key, Value> set(Node<Key, Value> n, Key k, Value v) {
        if (n == null) {
            this.size++;
            return new Node(k, v);
        }
        int c = k.compareTo(n.k);
        if (c == 0) {
            n.v = v;
        }
        if (c < 0) {
            n.left = set(n.left, k, v);
        }
        if (c > 0) {
            n.right = set(n.right, k, v);
        }
        return n;
    }

    public boolean contains(Key k) {
        return get(k)!=null;
    }

    public Value get(Key k) {
        Node<Key, Value> n = this.root;
        while (n != null) {
            int c = k.compareTo(n.k);
            if (c == 0) {
                return n.v;
            } else if (c < 0) {
                n = n.left;
            } else if (c > 0) {
                n = n.right;
            }
        }
        return null;
    }

    public int depth() {
        return 0;
    }

    public int size() {
        return this.size;
    }

    ;
}
