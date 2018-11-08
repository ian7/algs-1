import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
import edu.princeton.cs.algs4.TrieST;

import java.util.Arrays;

public class BurrowsWheeler {
    // apply Burrows-Wheeler transform, reading from standard input and writing to standard output
    public static void transform() {
        String s = BinaryStdIn.readString();
        LocalCircularSuffixArray csa = new LocalCircularSuffixArray(s);
        BinaryStdOut.write(getFirst(csa));
        BinaryStdOut.write(getT(csa));
    }

    private static String inverseTransform(int first, char[] t) {
        int[] next = findNext(t);
        char[] firstColumn = t.clone();
        Arrays.sort(firstColumn);
        char[] output = new char[t.length];
        int pointer = first;
        for (int i = 0; i < t.length; i++) {
            output[i] = firstColumn[pointer];
            pointer = next[pointer];
        }
        return String.valueOf(output);
    }

    private static String getT(LocalCircularSuffixArray csa) {
        return csa.getT();
    }

    private static int getFirst(LocalCircularSuffixArray csa) {
        for (int i = 0; i < csa.length(); i++) {
            if (csa.index(i) == 0) {
                return i;
            }
        }
        return -1;
    }

    // apply Burrows-Wheeler inverse transform, reading from standard input and writing to standard output
    public static void inverseTransform() {
        int first = BinaryStdIn.readInt();
        String s = BinaryStdIn.readString();
        String output = inverseTransform(first, s.toCharArray());
        BinaryStdOut.write(output);
    }


    private static int[] findNext(char[] t) {
        int[] next = new int[t.length];
        boolean[] exclusions = new boolean[t.length];
        char[] firstColumn = t.clone();
        Arrays.sort(firstColumn);
        for (int i = 0; i < t.length; i++) {
            char c = firstColumn[i];
            int indexInT = indexIn(c, t, exclusions);
            next[i] = indexInT;
            exclusions[indexInT] = true;
        }
        return next;
    }

    private static int indexIn(char c, char[] a, boolean[] exclusions) {
        for (int i = 0; i < a.length; i++) {
            if (!exclusions[i] && a[i] == c) {
                return i;
            }
        }
        return -1;
    }


    // if args[0] is '-', apply Burrows-Wheeler transform
    // if args[0] is '+', apply Burrows-Wheeler inverse transform
    public static void main(String[] args) {
        if (args[0].equals("-")) transform();
        else if (args[0].equals("+")) inverseTransform();
        else throw new IllegalArgumentException("Illegal command line argument");
    }

    private static class LocalCircularSuffixArray {
        private final char c[];
        private final Suffix[] suffixes;


        public LocalCircularSuffixArray(String s) {
            this.c = s.toCharArray();
            this.suffixes = new Suffix[s.length()];
            for (int i = 0; i < c.length; i++) {
                this.suffixes[i] = new Suffix(c, i);
            }
            Arrays.sort(this.suffixes);
        }   // circular suffix array of s

        private class Suffix implements Comparable<Suffix> {
            private final char[] s;
            private final int offset;

            Suffix(char[] s, int offset) {
                this.s = s;
                this.offset = offset;
            }

            public char getAt(int index) {
                return s[(index + offset) % s.length];
            }

            public char getT() {
                return getAt(s.length - 1);
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
            return this.suffixes[i].getOffset();
        }                 // returns index of ith sorted suffix

        private String getT() {
            final char[] c = new char[length()];
            for (int i = 0; i < length(); i++) {
                c[i] = this.suffixes[i].getT();
            }
            return String.valueOf(c);
        }
    }
}