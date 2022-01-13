import java.util.ListIterator;

public class LinkedStackOfStrings<Item> implements Iterable<Item> {

    private Node first = null;
    ListIterator<Item> iterator = new ListIterator<>();

    private class Node {
        Item item;
        Node next;
    }

    private boolean isEmpty() {
        return first == null;
    }

    public void push(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
    }

    public Item pop() {
        Item item = first.item;
        first = first.next;
        return item;
    }

    @Override
    public Iterator<Item> Iterator() {
        // TODO Auto-generated method stub
        return iterator;
    }

    class ListIterator<Item> implements Iterator<Item> {

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Item item = (Item) current.item;
            current = current.next;
            return item;
        }

        @Override
        public Item remoove() {
            try {
                throw new IllegalAccessException("Cannot call this method");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return null;
        }

    }

}
