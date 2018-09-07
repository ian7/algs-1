import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class MyDeque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int size;

    public MyDeque() {
        this.size = 0;
        this.first = null;
        this.last = null;
    }                           // construct an empty deque

    public static void main(String[] args) {

    }   // unit testing (optional)

    public boolean isEmpty() {
        return 0 == this.size;
    }                 // is the deque empty?

    public int size() {
        return this.size;
    }                        // return the number of items on the deque

    public void addFirst(Item item) {
        if (null == item) {
            throw new IllegalArgumentException();
        }
        Node n = new Node(item, this.first);
        this.first = n;

        if (isEmpty()) {
            this.last = n;
        }

        size++;
    }          // add the item to the front

    public void addLast(Item item) {
        if (null == item) {
            throw new IllegalArgumentException();
        }
        Node n = new Node(item, null);

        if (isEmpty()) {
            this.first = n;
        } else {
            this.last.setNext(n);
        }
        this.last = n;

        size++;

    }           // add the item to the end

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return null;
    }                // remove and return the item from the front

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return null;
    }                 // remove and return the item from the end

    public Iterator<Item> iterator() {
        Iterator<Item> it = new MyDequeInterator(this);
        return it;
    }        // return an iterator over items in order from front to end

    private class MyDequeInterator implements Iterator<Item> {
        private MyDeque<Item> md;
        private Node current;

        MyDequeInterator(MyDeque<Item> md) {
            this.md = md;
            this.current = md.first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            Node oldCurrent = current;
            this.current = this.current.next;
            return oldCurrent.item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class Node {
        private Item item;
        private Node next;

        public Node(Item item, Node next) {
            this.item = item;
            this.next = next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}