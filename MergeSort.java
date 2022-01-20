import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] A = { 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };
        sort(A, 0, A.length - 1);
        System.out.println(Arrays.toString(A));
    }

    private static void sort(int[] A, int l, int h) {
        if (l < h) {
            int mid = l + (h - l) / 2;
            sort(A, l, mid);
            sort(A, mid + 1, h);
            merge(A, l, mid, h);
        }
    }

    private static void merge(int[] a, int l, int mid, int h) {

        int N1 = mid - l + 1;
        int N2 = h - mid;

        int[] L = new int[N1];
        int[] R = new int[N2];

        for (int i = 0; i < N1; i++)
            L[i] = a[l + i];

        for (int i = 0; i < N2; i++)
            R[i] = a[mid + 1 + i];

        int i = 0, j = 0, k = l;

        while (i < N1 && j < N2) {
            if (L[i] < R[j]) {
                a[k] = L[i];
                i++;
            } else {
                a[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < N1) {
            a[k] = L[i];
            i++;
            k++;
        }

        while (j < N2) {
            a[k] = R[j];
            j++;
            k++;
        }

    }
}
