import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;

public class Permutation {
    private static RandomizedQueue<String> set = new RandomizedQueue<String>();

    public static void main(String[] args) {
        int ranNumbers = 0;
        if (args.length > 0) {
            ranNumbers = Integer.parseInt(args[0]);
        }
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            set.enqueue(item);
        }
        Iterator<String> subset = set.iterator();
        for (int i = 0; i < ranNumbers; i++) {
            StdOut.println(subset.next());
        }
    }
}
