import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Picture;

import java.awt.*;

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
    if( 0 == y || this.height()-1 == y ||
        0 == x || this.width()-1 == x ){
      return 1000;
    }
    return Math.sqrt(gradientX(x,y)+gradientY(x,y));
  }

  private double gradientX(int x, int y){
    final Color left = this.picture.get(x-1,y);
    final Color right = this.picture.get(x+1,y);
    return gradient(left, right);
  }

  private double gradientY(int x, int y){
    final Color top = this.picture.get(x,y-1);
    final Color bottom = this.picture.get(x,y+1);
    return gradient(top, bottom);
  }

  private double gradient(Color before, Color after) {
    final double r = after.getRed() - before.getRed();
    final double g = after.getGreen() - before.getGreen();
    final double b = after.getBlue() - before.getBlue();
    return r * r + g * g + b * b;
  }

  // energy of pixel at column x and row y
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
