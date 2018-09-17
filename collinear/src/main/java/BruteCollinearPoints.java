import java.util.ArrayList;

public class BruteCollinearPoints {
  Point[] points;

  public BruteCollinearPoints(Point[] points)    // finds all line segments containing 4 points
  {
    if (points == null) {
      throw new IllegalArgumentException();
    }
    for (int i = 0; i < points.length; i++) {
      for (int j = 0; j < points.length; j++) {
        if (points[i] == null || points[j] == null) {
          throw new IllegalArgumentException();
        }
        if (i != j && points[i].compareTo(points[j]) == 0) {
          throw new IllegalArgumentException();
        }
      }
    }
    this.points = points;
  }

  public int numberOfSegments()        // the number of line segments
  {
    int segmentsCount = 0;

    for( int i=0; i<this.points.length;i++){
      for( int j=i; j<this.points.length;j++){
        if( i==j ){
          continue;
        }
        for( int k=j; k<this.points.length;k++){
          if( k==i || k ==j ){
            continue;
          }
          for( int l=k; l<this.points.length;l++){
            if( l==k|| l==j || l==i){
              continue;
            }
            if( areCollinear(this.points[i],
                this.points[j],this.points[k],this.points[l])){
              segmentsCount++;
            }
          }
        }
      }
    }
    return segmentsCount;
  }

  public LineSegment[] segments() {
    final LineSegment[] segments = new LineSegment[numberOfSegments()];
    int segmentIndex = 0;

    for( int i=0; i<this.points.length;i++){
      for( int j=i; j<this.points.length;j++){
        if( i==j ){
          continue;
        }
        for( int k=j; k<this.points.length;k++){
          if( k==i || k ==j ){
            continue;
          }
          for( int l=k; l<this.points.length;l++){
            if( l==k|| l==j || l==i){
              continue;
            }
            if( areCollinear(this.points[i],
                this.points[j],this.points[k],this.points[l])){
              segments[segmentIndex++] = new LineSegment(this.points[i],this.points[l]);
            }
          }
        }
      }
    }
    return segments;
  }
  private boolean areCollinear( Point p1, Point p2, Point p3, Point p4){
    final double slope1 = p1.slopeTo(p2);
    final double slope2 = p2.slopeTo(p3);
    final double slope3 = p3.slopeTo(p4);
    if( slope1 == slope2 && slope2 == slope3 ){
      return true;
    }
    else{
      return false;
    }
  }

  /*
  private Segments[] pointSegments( int pointIndex ){
    final LineSegment[] segments = new LineSegment[this.points.length-1];
    for( int i=0; i<this.points.length;i++){
      segments[i] = new LineSegment( )
    }

  }
  */
}
