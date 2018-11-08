import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

import java.util.LinkedList;

public class MoveToFront {
    private final LinkedList<Character> order;

    public MoveToFront() {
        this.order = new LinkedList<>();
        for (char i = 0; i < 256; i++) {
            this.order.addLast(Character.valueOf(i));
        }
    }

    // apply move-to-front encoding, reading from standard input and writing to standard output
    public static void encode() {
        MoveToFront mtf = new MoveToFront();
        while (!BinaryStdIn.isEmpty()) {
            char c = BinaryStdIn.readChar();
            char i = (char) mtf.move(c);
            BinaryStdOut.write(i);
        }
        BinaryStdOut.flush();
    }

    private int move(char c) {
        int index = this.order.indexOf(c);
        this.order.remove(index);
        this.order.addFirst(c);
        return index;
    }

    private char deMove(int index) {
        char value = this.order.get(index);
        this.order.remove(index);
        this.order.addFirst(value);
        return value;
    }

    // apply move-to-front decoding, reading from standard input and writing to standard output
    public static void decode() {
        MoveToFront mtf = new MoveToFront();

        while (!BinaryStdIn.isEmpty()) {
            char i = BinaryStdIn.readChar();
            char c = mtf.deMove(i);
            BinaryStdOut.write(c);
        }
        BinaryStdOut.flush();
    }

    // if args[0] is '-', apply move-to-front encoding
    // if args[0] is '+', apply move-to-front decoding
    public static void main(String[] args) {
        if (args.length == 1) {
            if (args[0].compareTo("+") == 0) {
                decode();
                return;
            }
            if (args[0].compareTo("-") == 0) {
                encode();
                return;
            }
        } else {
            System.out.println("wrong number or arguments - should be exactly 1, either + or -");
        }
    }
}
