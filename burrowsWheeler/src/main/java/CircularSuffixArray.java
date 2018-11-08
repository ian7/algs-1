import edu.princeton.cs.algs4.TrieST;

import java.util.Iterator;

public class CircularSuffixArray {
    private final String s;
    private final char c[];
    private char spareC[];
    private char t[];
    private final TrieST<Integer> tst;
    private final int indices[];

    public CircularSuffixArray(String s) {
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

    public static void main(String[] args) {
    } // unit testing (required)
}