import edu.princeton.cs.algs4.StdRandom;

//the selection problems consists of finding the smallest kth element of an Array
//this can be done in average Linear time
public class Selection {
    public static void main(String[] args) {
        int[] a = { 9, 8, 7, 6, 5, 4, 3, 2, 1 };
        int k = select(a, 5);
        System.out.println(k);
    }

    private static int select(int[] a, int k) {
        int lo = 0;
        int hi = a.length - 1;
        StdRandom.shuffle(a);
        while (hi > lo) {
            int j = partition(a, lo, hi);
            if (j > k)
                hi = j - 1;
            else if (j < k)
                lo = j + 1;
            else
                return a[k];
        }
        return a[k];
    }

    private static int partition(int[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        while (true) {
            while (a[++i] < a[lo])
                if (i == hi)
                    break;
            while (a[--j] > a[lo])
                if (j == lo)
                    break;
            if (i >= j)
                break;
            swap(a, i, j);
        }
        swap(a, lo, j);
        return j;
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
