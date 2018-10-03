import edu.princeton.cs.algs4.Picture;

import java.awt.Color;

public class SeamCarver {
  private Picture picture;
  private double[] seamSums;
  private int[] pathTo;

  public SeamCarver(Picture picture) {
    if (picture == null) {
      throw new IllegalArgumentException();
    }
    this.picture = new Picture(picture);
    final int size = this.height() * this.width();
    this.pathTo = new int[size];
    this.seamSums = new double[size];
    this.fillIn();
  }                // create a seam carver object based on the given picture

  private void fillIn() {
    for (int y = 0; y < this.height(); y++) {
      for (int x = 0; x < this.width(); x++) {
        final int focusedIndex = index(x, y);
        this.pathTo[focusedIndex] = -1;
        this.seamSums[focusedIndex] = Integer.MAX_VALUE;
      }
    }
  }

  private int index(int x, int y) {
    return y * this.width() + x;
  }

  public Picture picture() {
    return new Picture(picture);
  }                          // current picture

  public int width() {
    return picture.width();
  }                            // width of current picture

  public int height() {
    return picture.height();
  }                          // height of current picture

  public double energy(int x, int y) {
    if (x < 0 || x >= this.width() ||
        y < 0 || y >= this.height()) {
      throw new IllegalArgumentException();
    }
    return this.calculateEnergy(x, y);
  }

  private double calculateEnergy(int x, int y) {
    if (0 == y || this.height() - 1 == y ||
        0 == x || this.width() - 1 == x) {
      return 1000;
    }
    return Math.sqrt(gradientX(x, y) + gradientY(x, y));
  }

  private double gradientX(int x, int y) {
    final Color left = this.picture.get(x - 1, y);
    final Color right = this.picture.get(x + 1, y);
    return gradient(left, right);
  }

  private double gradientY(int x, int y) {
    final Color top = this.picture.get(x, y - 1);
    final Color bottom = this.picture.get(x, y + 1);
    return gradient(top, bottom);
  }

  private double gradient(Color before, Color after) {
    final double r = after.getRed() - before.getRed();
    final double g = after.getGreen() - before.getGreen();
    final double b = after.getBlue() - before.getBlue();
    return r * r + g * g + b * b;
  }

  // energy of pixel at column x and row y
  public int[] findHorizontalSeam() {
    // let's initialize first collumn
    for (int y = 0; y < this.height(); y++) {
      this.seamSums[index(0, y)] = energy(0, y);
    }

    // go through all the other rows
    for (int x = 1; x < this.width(); x++) {
      for (int y = 0; y < this.height(); y++) {
        int bestPredecessor = index(x - 1, y);
        if (y > 0 &&
            this.seamSums[index(x - 1, y - 1)] < this.seamSums[bestPredecessor]) {
          bestPredecessor = index(x - 1, y - 1);
        }
        if (y < this.height() - 1 &&
            this.seamSums[index(x - 1, y + 1)] < this.seamSums[bestPredecessor]) {
          bestPredecessor = index(x - 1, y + 1);
        }
        this.pathTo[index(x, y)] = bestPredecessor;
        this.seamSums[index(x, y)] = this.seamSums[bestPredecessor] + energy(x, y);
      }
    }

    // find the lowest sum in the last row
    int lowestSum = 0;
    for (int y = 1; y < this.height(); y++) {
      if (this.seamSums[index(this.width() - 1, lowestSum)] >
          this.seamSums[index(this.width() - 1, y)]) {
        lowestSum = y;
      }
    }

    // list the seam by traversing it backwards
    int[] seamIndices = new int[this.width()];
    int predecessorIndex = this.pathTo[index(this.width() - 1, lowestSum)];
    seamIndices[this.width() - 1] = lowestSum;

    for (int x = this.width() - 2; x >= 0; x--) {
      seamIndices[x] = predecessorIndex / this.width();
      predecessorIndex = this.pathTo[predecessorIndex];
    }

    return seamIndices;
  }              // sequence of indices for horizontal seam

  public int[] findVerticalSeam() {
    // let's initialize first row
    for (int x = 0; x < this.width(); x++) {
      this.seamSums[index(x, 0)] = energy(x, 0);
    }

    // go through all the other rows
    for (int y = 1; y < this.height(); y++) {
      for (int x = 0; x < this.width(); x++) {
        int bestPredecessor = index(x, y - 1);
        if (x > 0 &&
            this.seamSums[index(x - 1, y - 1)] < this.seamSums[bestPredecessor]) {
          bestPredecessor = index(x - 1, y - 1);
        }
        if (x < this.width() - 1 &&
            this.seamSums[index(x + 1, y - 1)] < this.seamSums[bestPredecessor]) {
          bestPredecessor = index(x + 1, y - 1);
        }
        this.pathTo[index(x, y)] = bestPredecessor;
        this.seamSums[index(x, y)] = this.seamSums[bestPredecessor] + energy(x, y);
      }
    }

    // find the lowest sum in the last row
    int lowestSum = 0;
    for (int x = 1; x < this.width(); x++) {
      if (this.seamSums[index(lowestSum, this.height() - 1)] >
          this.seamSums[index(x, this.height() - 1)]) {
        lowestSum = x;
      }
    }

    // list the seam by traversing it backwards
    int[] seamIndices = new int[this.height()];
    int predecessorIndex = this.pathTo[index(lowestSum, this.height() - 1)];
    seamIndices[this.height() - 1] = lowestSum;

    for (int y = this.height() - 2; y >= 0; y--) {
      seamIndices[y] = predecessorIndex % this.width();
      predecessorIndex = this.pathTo[predecessorIndex];
    }

    return seamIndices;
  }                 // sequence of indices for vertical seam

  public void removeHorizontalSeam(int[] seam) {
    if (seam == null || !validateHorizontalSeam(seam)) {
      throw new IllegalArgumentException();
    }

    Picture newPicture = new Picture(this.width(), this.height() - 1);

    for (int x = 0; x < this.width(); x++) {
      for (int y = 0; y < this.height() - 1; y++) {
        if (seam[x] > y) {
          newPicture.set(x, y, this.picture.get(x, y));
        } else {
          newPicture.set(x, y, this.picture.get(x, y + 1));
        }
      }
    }
    this.picture = newPicture;
  }

  // remove horizontal seam from current picture
  public void removeVerticalSeam(int[] seam) {
    if (seam == null || !validateVerticalSeam(seam)) {
      throw new IllegalArgumentException();
    }
    Picture newPicture = new Picture(this.width() - 1, this.height());

    for (int x = 0; x < this.width() - 1; x++) {
      for (int y = 0; y < this.height(); y++) {
        if (seam[y] > x) {
          newPicture.setRGB(x, y, this.picture.getRGB(x, y));
        } else {
          newPicture.setRGB(x, y, this.picture.getRGB(x + 1, y));
        }
      }
    }
    this.picture = newPicture;
  }     // remove vertical seam from current picture

  private boolean validateHorizontalSeam(int[] seam) {
    if (seam.length != this.width()) {
      return false;
    }
    for (int i = 0; i < seam.length; i++) {
      if (i > 0 && Math.abs(seam[i] - seam[i - 1]) > 1) {
        return false;
      }
      if (seam[i] < 0 || seam[i] >= this.height())
        return false;
    }
    return true;
  }

  private boolean validateVerticalSeam(int[] seam) {
    if (seam.length != this.height()) {
      return false;
    }
    for (int i = 0; i < seam.length; i++) {
      if (i > 0 && Math.abs(seam[i] - seam[i - 1]) > 1) {
        return false;
      }
      if (seam[i] < 0 || seam[i] >= this.width())
        return false;
    }
    return true;
  }
}
