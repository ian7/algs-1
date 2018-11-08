import java.util.Arrays;

public class CircularSuffixArray {
    private final char c[];
    private final Suffix[] suffixes;


    public CircularSuffixArray(String s) {
        if (s == null) {
            throw new IllegalArgumentException();
        }
        this.c = s.toCharArray();
        this.suffixes = new Suffix[s.length()];
        for (int i = 0; i < c.length; i++) {
            this.suffixes[i] = new Suffix(c, i);
        }
        Arrays.sort(this.suffixes);
    }   // circular suffix array of s

    private class Suffix implements Comparable<Suffix> {
        final private char[] s;
        final private int offset;

        Suffix(char[] s, int offset) {
            this.s = s;
            this.offset = offset;
        }

        char getAt(int index) {
            return s[(index + offset) % s.length];
        }

        public int getOffset() {
            return this.offset;
        }

        @Override
        public int compareTo(Suffix that) {
            for (int i = 0; i < this.s.length; i++) {
                final char thisAt = this.getAt(i);
                final char thatAt = that.getAt(i);
                if (thisAt > thatAt) {
                    return 1;
                }
                if (thisAt < thatAt) {
                    return -1;
                }
            }
            return 0;
        }
    }

    public int length() {
        return this.c.length;
    }                     // length of s

    public int index(int i) {
        if (i < 0 || i >= length()) {
            throw new IllegalArgumentException();
        }
        return this.suffixes[i].getOffset();
    }                 // returns index of ith sorted suffix

    public static void main(String[] args) {
        // nothing going on here
    } // unit testing (required)
}