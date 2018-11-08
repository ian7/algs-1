import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
import edu.princeton.cs.algs4.Queue;


public class BurrowsWheeler {
    // apply Burrows-Wheeler transform, reading from standard input and writing to standard output
    public static void transform() {
        String s = BinaryStdIn.readString();
        CircularSuffixArray csa = new CircularSuffixArray(s);
        BinaryStdOut.write(getFirst(csa));
        BinaryStdOut.write(getT(s, csa));
        BinaryStdOut.close();
    }

    private static char[] bucketSort(char[] c){
        final int radix = 256;
        final int buckets[] = new int[radix];
        final char[] output = new char[c.length];
        for( int i=0;i<c.length;i++){
            buckets[c[i]]++;
        }
        int pointer = 0;
        for( char i=0;i<radix;i++){
            final int elementsInBucket = buckets[i];
            for( int j=0;j<elementsInBucket;j++){
                output[pointer+j]=i;
            }
            pointer+=elementsInBucket;
        }
        return output;
    }

    private static String inverseTransform(int first, char[] t) {
        char[] firstColumn = bucketSort(t);
        int[] next = findNext(t);
        char[] output = new char[t.length];
        int pointer = first;
        for (int i = 0; i < t.length; i++) {
            output[i] = firstColumn[pointer];
            pointer = next[pointer];
        }
        return String.valueOf(output);
    }

    private static String getT(String s, CircularSuffixArray csa) {
        final char[] c = new char[csa.length()];
        for (int i = 0; i < c.length; i++) {
            c[i] = s.charAt((c.length - 1 + csa.index(i)) % c.length);
        }
        return String.valueOf(c);
    }

    private static int getFirst(CircularSuffixArray csa) {
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
        BinaryStdOut.close();
    }


    private static int[] findNext(char[] t) {
        final int radix = 256;
        int[] next = new int[t.length];
        char[] firstColumn = bucketSort(t);

        Queue<Integer>[] tIndices = (Queue<Integer>[]) new Queue[radix];

        for( int i=0;i<radix;i++){
            tIndices[i] = new Queue<>();
        }
        for( int i=0; i<t.length;i++){
            tIndices[t[i]].enqueue(i);
        }
        for (int i = 0; i < t.length; i++) {
            char c = firstColumn[i];
            next[i] = tIndices[c].dequeue();
        }
        return next;
    }

    // if args[0] is '-', apply Burrows-Wheeler transform
    // if args[0] is '+', apply Burrows-Wheeler inverse transform
    public static void main(String[] args) {
        if (args[0].equals("-")) transform();
        else if (args[0].equals("+")) inverseTransform();
        else throw new IllegalArgumentException("Illegal command line argument");
    }
}