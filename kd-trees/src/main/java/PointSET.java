import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PointSET {
  private int size;
  private final SET<Point2D> points;

  public PointSET() {
    this.size = 0;
    this.points = new SET<>();
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
    this.points.add(p);
    this.size = this.points.size();
  }              // add the point to the set (if it is not already in the set)

  public boolean contains(Point2D p) {
    if (p == null) {
      throw new IllegalArgumentException();
    }
    return (this.points.contains(p));
  }           // does the set contain point p?

  public void draw() {
    // does nothing
  }                        // draw all points to standard draw

  public Iterable<Point2D> range(RectHV rect) {
    if (rect == null) {
      throw new IllegalArgumentException();
    }

    List<Point2D> ps = new ArrayList<>();

    Iterator<Point2D> i = this.points.iterator();
    while (i.hasNext()) {
      Point2D p = i.next();
      if (rect.contains(p)) {
        ps.add(p);
      }
    }
    return ps;
  }             // all points that are inside the rectangle (or on the boundary)

  public Point2D nearest(Point2D p) {
    if (p == null) {
      throw new IllegalArgumentException();
    }
    Point2D nearest = null;
    double nearestDistance = 0;

    Iterator<Point2D> i = this.points.iterator();

    while (i.hasNext()) {
      Point2D nextPoint = i.next();
      double nextPointDistance = nextPoint.distanceSquaredTo(p);

      if (nearest == null ||
          nearestDistance > nextPointDistance) {
        nearest = nextPoint;
        nearestDistance = nextPointDistance;
      }
    }
    return nearest;
  }             // a nearest neighbor in the set to point p; null if the set is empty

  public static void main(String[] args) {

    // nothing done here - testing in spock

  }                // unit testing of the methods (optional)
}
