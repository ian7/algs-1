import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;

import java.util.ArrayList;

public class KdTree {
  private int size;
  private KdNode root;

  public KdTree() {
    this.size = 0;
    this.root = null;
  }                              // construct an empty set of points

  public boolean isEmpty() {

    return this.size == 0;
  }                      // is the set empty?

  public int size() {

    return this.size;
  }                        // number of points in the set

  public void insert(Point2D p) {
    if (p == null) {
      throw new IllegalArgumentException();
    }
    if (root == null) {
      this.root = new KdNode(p);
    } else {
      KdNode pointer = this.root;
      boolean done = false;
      do {
        if (pointer.compareTo(p)>0){
          if( pointer.getLeft() != null ){
            pointer = pointer.getLeft();
            continue;
          }
          // assign it
          pointer.setLeft( new KdNode(p,pointer ));
          done = true;
        }
        else{
          if( pointer.getLeft() != null ){
            pointer = pointer.getRight();
            continue;
          }
          // assign it
          pointer.setRight( new KdNode(p,pointer ));
          done = true;
        }
      }
      while(!done);
    }
    this.size++;
  }              // add the point to the set (if it is not already in the set)

  public boolean contains(Point2D p) {
    if (p == null) {
      throw new IllegalArgumentException();
    }

    KdNode pointer = root;

    while( pointer!= null ){
      int pointerComparedToP = pointer.compareTo(p);
      if( pointerComparedToP==0){
        return true;
      }
      if( pointerComparedToP > 0 ){
          pointer = pointer.getLeft();
      }
      else{
        pointer = pointer.getRight();
      }
    }

    return false;
  }           // does the set contain point p?

  public void draw() {

  }                        // draw all points to standard draw

  public Iterable<Point2D> range(RectHV rect) {
    if (rect == null) {
      throw new IllegalArgumentException();
    }
    return null;
  }             // all points that are inside the rectangle (or on the boundary)

  public Point2D nearest(Point2D p) {
    if (p == null) {
      throw new IllegalArgumentException();
    }

    return null;
  }             // a nearest neighbor in the set to point p; null if the set is empty

  public static void main(String[] args) {

  }                // unit testing of the methods (optional)

  private class KdNode implements Comparable<KdNode> {
    private final KdNode predecessor;
    private final boolean isHorizontal;
    private final Point2D point;
    private KdNode left;
    private KdNode right;

    public KdNode(Point2D p) {
      this(p, null);
    }

    public KdNode(Point2D p, KdNode predecessor) {
      this.point = p;
      if (predecessor == null) {
        this.isHorizontal = false;
      } else {
        this.isHorizontal = !predecessor.isHorizontal;
      }
      this.predecessor = predecessor;
      this.left = null;
      this.right = null;
    }

    @Override
    public int compareTo(KdNode that) {
      return (compareTo(that.point));
    }

    public int compareTo(Point2D point) {
      if (this.isHorizontal) {
        // horizontal
        return Double.compare(this.point.y(), point.y());
      } else {
        // vertical
        return Double.compare(this.point.x(), point.x());
      }

    }

    public KdNode getLeft() {
      return left;
    }

    public void setLeft(KdNode left) {
      this.left = left;
    }

    public KdNode getRight() {
      return right;
    }

    public void setRight(KdNode right) {
      this.right = right;
    }
  }
}
