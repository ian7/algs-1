import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    public RandomizedQueue(){

    }                 // construct an empty randomized queue
    public boolean isEmpty(){
        return false;
    }                 // is the randomized queue empty?
    public int size(){
        return 0;
    }                        // return the number of items on the randomized queue
    public void enqueue(Item item){
    }           // add the item
    public Item dequeue(){
        return null;
    }                    // remove and return a random item
    public Item sample(){
        return null;
    }                     // return a random item (but do not remove it)
    public Iterator<Item> iterator(){
        return null;
    }         // return an independent iterator over items in random order
    public static void main(String[] args) {

    }  // unit testing (optional)
}