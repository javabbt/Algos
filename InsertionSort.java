import java.util.Arrays;

public class InsertionSort {
    public static void main(String[] args) {
        int[] a = { 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };
        sort(a);
        System.out.println(Arrays.toString(a));
    }

    static void sort(int[] A) {
        int N = A.length;
        for (int j = 1; j < N; j++) {
            int key = A[j];
            int i = j - 1;
            while (i >= 0 && A[i] > key) {
                A[i + 1] = A[i];
                i--;
            }
            A[i + 1] = key;
        }
    }
}
