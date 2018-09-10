import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
  private Node first;
  private Node last;
  private int size;

  public Deque() {
    this.size = 0;
    this.first = null;
    this.last = null;
  }                           // construct an empty deque

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
    final Item current = this.first.item;
    this.first = this.first.next;
    this.size--;
    return current;
  }                // remove and return the item from the front

  public Item removeLast() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    final Item current = this.last.item;

    if (1 == size) {
      this.last = null;
      this.first = null;
    } else {
      Node n = this.first;
      while (n.next != this.last) {
        n = n.next;
      }
      n.next = null;
      this.last = n;
    }
    this.size--;
    return current;
  }        // return an iterator over items in order from front to end

  public Iterator<Item> iterator() {
    return new MyDequeInterator(this);
  }

  private class MyDequeInterator implements Iterator<Item> {
    private Node current;

    MyDequeInterator(Deque<Item> md) {
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
    private final Item item;
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