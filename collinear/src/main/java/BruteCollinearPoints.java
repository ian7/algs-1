public class BruteCollinearPoints {
  Point[] points;

  public BruteCollinearPoints(Point[] points)    // finds all line segments containing 4 points
  {
    if( points == null){
      throw new IllegalArgumentException();
    }
    for( int i = 0; i < points.length; i++){
      for( int j=0; j < points.length; j++ ){
        if( points[i] == null || points[j] == null){
          throw new IllegalArgumentException();
        }
        if( i!=j && points[i].compareTo(points[j]) == 0){
          throw new IllegalArgumentException();
        }
      }
    }
    this.points = points;
  }

  public int numberOfSegments()        // the number of line segments
  {
    return this.points.length;
  }

  public LineSegment[] segments() {
    return null;
  }
}
