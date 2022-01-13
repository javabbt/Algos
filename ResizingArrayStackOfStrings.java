public class ResizingArrayStackOfStrings {
    String[] S;
    int N;

    public ResizingArrayStackOfStrings() {
        S = new String[1];
    }

    public void push(String item) {
        if (N == S.length)
            resize(2 * N);
        S[N++] = item;
    }

    private void resize(int capacity) {
        String[] copy = new String[capacity];
        for (int i = 0; i < N; i++) {
            copy[i] = S[i];
        }
        S = copy;
    }

    public String pop() {
        String item = S[--N];
        S[N] = null;
        return item;
    }
}
