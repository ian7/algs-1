import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
  private int size;
  private Item[] items;
  private int capacity;

  public RandomizedQueue() {
    this.size = 0;
    this.capacity = 0;
    this.items = null;
  }                 // construct an empty randomized queue

  private int calculateNewCapacity() {
    if (capacity == 0) {
      return 1;
    }
    if (size >= capacity) {
      return size * 2;
    }
    if (size < capacity / 4) {
      return size / 2;
    }
    return capacity;
  }

  private void adjust() {
    final int newCapacity = calculateNewCapacity();
    if (newCapacity != capacity) {
      Item[] newItems = (Item[]) new Object[newCapacity];
      capacity = newCapacity;
      for (int i = 0; i < size; i++) {
        newItems[i] = this.items[i];
        this.items[i] = null;
      }
      this.items = newItems;
    }
  }

  public boolean isEmpty() {
    return this.size == 0;
  }                 // is the randomized queue empty?

  public int size() {
    return this.size;
  }                        // return the number of items on the randomized queue

  public void enqueue(Item item) {
    if (item == null) {
      throw new IllegalArgumentException();
    }
    adjust();
    this.items[size] = item;
    this.size++;
  }           // add the item

  public Item dequeue() {
    if (size == 0) {
      throw new NoSuchElementException();
    }
    final int randomIndex = StdRandom.uniform(size);
    final Item current = this.items[randomIndex];
    size--;
    this.items[randomIndex] = this.items[size];
    this.items[size] = null;
    return current;
  }                    // remove and return a random item

  public Item sample() {
    if (size == 0) {
      throw new NoSuchElementException();
    }

    final int randomIndex = StdRandom.uniform(size);
    return this.items[randomIndex];
  }                     // return a random item (but do not remove it)

  public Iterator<Item> iterator() {
    return new RandomIterator<Item>(this);
  }         // return an independent iterator over items in random order

  private class RandomIterator<Item> implements Iterator<Item> {
    private final RandomizedQueue<Item> randomizedQueue;

    RandomIterator(RandomizedQueue<Item> randomizedQueue) {
      this.randomizedQueue = randomizedQueue;
    }

    @Override
    public boolean hasNext() {
      return false;
    }

    @Override
    public Item next() {
      if (randomizedQueue.isEmpty()) {
        throw new NoSuchElementException();
      }
      return null;
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }
}