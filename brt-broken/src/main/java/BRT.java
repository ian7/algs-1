public class BRT<Key extends Comparable<Key>, Value> {
    Node<Key, Value> root;
    int size;

    BRT() {
        this.root = null;
    }

    private class Node<Key, Value> {
        private Node left;
        private Node right;
        private Value v;
        private final Key k;

        Node(Key k, Value v) {
            this.k = k;
            this.v = v;
            this.left = null;
            this.right = null;
        }

        public void setValue(Value v) {
            this.v = v;
        }
    }

    public void add(Key k, Value v) {
        Node<Key, Value> probe = this.find(k);
        if (probe != null) {
            probe.setValue(v);
        } else {
            Node<Key, Value> newOne = new Node(k, v);
            if (this.root == null) {
                this.root = newOne;
            } else {
                Node<Key, Value> pointer = this.root;
                while (pointer != null) {
                    if (pointer.k.compareTo(k) > 0) {
                        if (pointer.left == null) {
                            pointer.left = newOne;
                            break;
                        } else {
                            pointer = pointer.left;
                        }
                    }
                    if (pointer.k.compareTo(k) < 0) {
                        if (pointer.right == null) {
                            pointer.right = newOne;
                            break;
                        } else {
                            pointer = pointer.right;
                        }
                    }
                }
            }
            size++;
        }
    }

    public boolean contains(Key k) {
        return find(k) != null;
    }

    private Node find(Key k) {
        Node<Key, Value> pointer = this.root;
        while (pointer != null && pointer.k.compareTo(k) != 0) {
            if (pointer.k.compareTo(k) > 0) {
                pointer = pointer.left;
                continue;
            }
            if (pointer.k.compareTo(k) < 0) {
                pointer = pointer.right;
                continue;
            }
        }
        if (pointer == null) {
            return null;
        } else {
            return pointer;
        }
    }

    public Value get(Key k) {
        Node<Key, Value> probe = this.find(k);
        if (probe != null) {
            return probe.v;
        } else {
            return null;
        }
    }

    public int size() {
        return this.size;
    }
}
