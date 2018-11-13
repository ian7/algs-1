public class BRT<Key extends Comparable<Key>, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
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
        private boolean color;


        public Node(Key k, Value v) {
            this.k = k;
            this.v = v;
            this.left = null;
            this.right = null;
            this.color = RED;
        }

        public boolean isRed(){
            return this.color==RED;
        }
    }

    public int maxDepth(){
        return maxDepth(this.root);
    }

    private int maxDepth(Node<Key, Value> n ){
        if( n == null){
            return 0;
        }
        else{
            return Math.max( maxDepth( n.left ), maxDepth(n.right))+1;
        }
    }

    public Node<Key, Value> set(Key k, Value v) {
        this.root = set(this.root, k, v);
        this.root.color = BLACK;
        return this.root;
    }

    private boolean isRed( Node<Key, Value> n ){
        if( n == null ){
            return false;
        }
        else{
            return n.isRed();
        }
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


        if( isRed(n.right) && !isRed(n.left) ){
            n = rotateLeft(n);
        }

        if( isRed(n.left) && isRed(n.left.left)){
            n = rotateRight(n);
        }

        if( isRed(n.left) && isRed(n.right)){
            flipColors(n);
        }

        return n;
    }

    Node<Key, Value> rotateLeft( Node<Key, Value> h ){
        Node<Key, Value> x = h.right;
        x.color = h.color;
        h.color = RED;
        h.right = x.left;
        x.left = h;
        return x;
    }

    Node<Key, Value> rotateRight( Node<Key, Value> h){
        Node<Key, Value> x = h.left;
        x.color = h.color;
        h.color = RED;
        h.left = x.right;
        x.right = h;
        return x;
    }

    void flipColors(Node<Key, Value> n){
        n.color = !n.color;
        n.left.color = !n.left.color;
        n.right.color = !n.right.color;
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
        return maxDepth();
    }

    public int size() {
        return this.size;
    }

    ;
}
