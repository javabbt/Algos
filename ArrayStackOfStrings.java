public class ArrayStackOfStrings {

    String[] S;
    int N;

    public ArrayStackOfStrings(int capacity) {
        S = new String[capacity];
    }

    public void push(String item) {
        S[N++] = item;
    }

    public String pop() {
        String item = S[--N];
        S[N] = null;
        return item;
    }

}
