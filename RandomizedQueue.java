import java.util.NoSuchElementException;
import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] a; // array of items
    private int N; // number of items in randomized queue

    // initializes an empty randomized queue
    public RandomizedQueue() {
        a = (Item[]) new Object[2];
        N = 0;
    }

    // checks if randomized queue contains any items
    public boolean isEmpty() {
        return N == 0;
    }

    // returns number of items in randomized queue
    public int size() {
        return N;
    }

    // resizes the array in which randomized queue items are contained
    private void resize(int capacity) {
        assert capacity >= N;
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    // adds item to randomized queue
    public void enqueue(Item item) {
        if (N == a.length)
            resize(2 * a.length); // doubles size of queue
        int index = StdRandom.uniform(N + 1);
        if (index != N)
            a[N] = a[index];
        a[index] = item;
        N++;
    }

    // removes and returns a random item in randomized queue
    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException("No items in queue");
        Item item = a[N - 1];
        a[N - 1] = null; // to avoid loitering
        N--;
        // shrink size of array if necessary
        if (N > 0 && N == a.length / 4)
            resize(a.length / 2);
        return item;
    }

    // returns, but does not remove, a random item in randomized queue
    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException("No items in queue");
        int index = StdRandom.uniform(N);
        return a[index];
    }

    // returns an iterator that cycles through queue in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int i;
        private final Item[] shuffledArray;

        public RandomizedQueueIterator() {
            i = 0;
            shuffledArray = (Item[]) new Object[N];
            for (int j = 0; j < N; j++) {
                shuffledArray[j] = a[j];
            }
            StdRandom.shuffle(shuffledArray); // iterator array shuffle
        }

        public boolean hasNext() {
            return i < shuffledArray.length;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            return shuffledArray[i++];
        }
    }

    // unit testing
    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-"))
                rq.enqueue(item);
            else if (!rq.isEmpty())
                StdOut.println(rq.dequeue());
        }
    }
}
