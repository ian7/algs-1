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
        private final String s;
        private final char c[];
        private char spareC[];
        private char t[];
        TrieST<Integer> tst;
        private final int indices[];

        public LocalCircularSuffixArray(String s) {
            this.tst = new TrieST<>();
            this.s = s;
            this.c = new char[s.length()];
            this.t = new char[s.length()];
            this.spareC = new char[s.length()];
            this.indices = new int[s.length()];
            for (int i = 0; i < s.length(); i++) {
                c[i] = s.charAt(i);
            }
            for (int i = 0; i < s.length(); i++) {
                tst.put(get(i), i);
            }
            int i = 0;
            for (String prefix : tst.keys()) {
                this.indices[i] = tst.get(prefix);
                this.t[i] = prefix.charAt(s.length() - 1);
                i++;
            }
        }   // circular suffix array of s

        private String get(int offset) {
            for (int i = 0; i < s.length(); i++) {
                int effectiveOffset = (i + offset) % s.length();
                this.spareC[i] = s.charAt(effectiveOffset);
            }
            return String.valueOf(spareC);
        }

        public int length() {
            return s.length();
        }                     // length of s

        public int index(int i) {
            return this.indices[i];
        }                 // returns index of ith sorted suffix

        private String getT() {
            return String.valueOf(this.t);
        }
    }
}