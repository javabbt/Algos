import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] a = { 9, 8, 7, 6, 5, 4, 3, 2, 1 };
        sort(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
    }

    private static void sort(int[] a, int lo, int hi) {
        if (lo >= hi)
            return;
        int j = partition(a, lo, hi);
        sort(a, lo, j);
        sort(a, j + 1, hi);
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
