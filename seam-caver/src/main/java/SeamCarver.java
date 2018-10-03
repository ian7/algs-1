import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Picture;

public class SeamCarver {
  private final Picture picture;
  Digraph digraph;
  public SeamCarver(Picture picture){
    if( picture == null ){
      throw new IllegalArgumentException();
    }
    this.picture = picture;
    this.digraph = new Digraph(this.height()*this.width());
  }                // create a seam carver object based on the given picture
  private void fillDigraph(){
    //this.digraph
  }
  public Picture picture(){
    return picture;
  }                          // current picture
  public     int width(){
    return picture.width();
  }                            // width of current picture
  public     int height() {
    return picture.height();
  }                          // height of current picture
  public  double energy(int x, int y)  {
    return 0;
  }              // energy of pixel at column x and row y
  public   int[] findHorizontalSeam() {
    return null;
  }              // sequence of indices for horizontal seam
  public   int[] findVerticalSeam(){
    return null;
  }                 // sequence of indices for vertical seam
  public    void removeHorizontalSeam(int[] seam) {
    if (seam == null) {
      throw new IllegalArgumentException();
    }
  }
    // remove horizontal seam from current picture
  public    void removeVerticalSeam(int[] seam){
      if(seam==null){
        throw new IllegalArgumentException();
      }
  }     // remove vertical seam from current picture
}
