import java.util.Arrays;
import java.util.Random;

public class ShuffleArray {
    public static void main(String[] args) {
        int[] a = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 };
        Random random = new Random();
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int r = random.nextInt(i + 1);
            swap(a, i, r);
        }
        System.out.println(Arrays.toString(a));
    }

    static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
