import java.util.Comparator;

public class Point implements Comparable<Point> {
  private int x;
  private int y;

  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }// constructs the point (x, y)

  public void draw() {
  }// draws this point

  public void drawTo(Point that) {
  }// draws the line segment from this point to that point

  public String toString() {
    return null;
  }// string representation

  public int compareTo(Point that) {
    if( that.y > this.y ){
      return -1;
    }
    if( that.y < this.y ){
      return +1;
    }
    if( that.x > this.x ){
      return -1;
    }
    if( that.x < this.x ){
      return +1;
    }
    return 0;
  }// compare two points by y-coordinates, breaking ties by x-coordinates

  public double slopeTo(Point that) {
    return 0d;
  }// the slope between this point and that point

  public Comparator<Point> slopeOrder() {
    // compare two points by slopes they make with this point
    return null;
  }
}
