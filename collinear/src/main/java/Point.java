import java.util.Comparator;

public class Point implements Comparable<Point> {
  private final int x;
  private final int y;

  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  } // constructs the point (x, y)

  public void draw() {
    // draws this point
  }

  public void drawTo(Point that) {
    // draws the line segment from this point to that point
  }

  public String toString() {
    return new StringBuilder().append(x).append(" ").append(y).toString();
  } // string representation

  public int compareTo(Point that) {
    if (that == null) {
      throw new NullPointerException();
    }

    if (this.y < that.y || (this.y == that.y && this.x < that.x)) {
      return -1;
    }

    if (this.y > that.y || (this.y == that.y && this.x > that.x)) {
      return 1;
    }


    return 0;
  } // compare two points by y-coordinates, breaking ties by x-coordinates

  public double slopeTo(Point that) {
    if (that == null) {
      throw new NullPointerException();
    }


    if (that.x == this.x) {
      if (that.y == this.y) {
        // degenerate
        return Double.NEGATIVE_INFINITY;
      }
      // vertical
      return Double.POSITIVE_INFINITY;
    } else {
      // horizontal
      if (that.y == this.y) {
        return 0;
      }
    }
    final double deltaY = that.y - this.y;
    final double deltaX = that.x - this.x;
    return deltaY / deltaX;
  } // the slope between this point and that point

  public Comparator<Point> slopeOrder() {
    return new PointComparator(this);
  }

  private class PointComparator implements Comparator<Point> {

    private final Point origin;

    PointComparator(Point origin) {
      this.origin = origin;
    }

    @Override
    public int compare(Point p1, Point p2) {
      return Double.compare(this.origin.slopeTo(p1), this.origin.slopeTo(p2));
    }
  }
}
