public class Queue<Item> {

    private Node first = null;
    private Node last = null;

    private class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public Item dequeue(Item item) {
        Item str = first.item;
        first = first.next;
        if (isEmpty())
            last = null;
        return str;
    }

    public void enqueue(Item item) {
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty())
            first = last;
        else
            oldLast.next = last;
    }
}
