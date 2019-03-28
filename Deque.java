package A02RandomizedQueuesAndDeques;

import edu.princeton.cs.introcs.StdOut;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Adam Gardner and Cesar Gonzalez on 10/8/16.
 */
public class Deque<Item> implements Iterable<Item> {

    private Node first, last;
    int size;

    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    private class Node{
        Item item;
        Node next, before;
    }


    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size;
    }

    //Adds an item to the beginning of the Linked-List and points the new first item to the old one.
    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException("The item passed as argument cannot be null");
        }
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        if (isEmpty()) {
            last = first;
        }else {
            first.next = oldFirst;
            oldFirst.before = first;
        }
        size++;
    }

    //Adds to the end of the list and points the old last to the new one.
    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException("The item passed as argument cannot be null");
        }
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
           first = last;
        }else {
            oldLast.next = last;
            last.before = oldLast;
        }
        size++;
    }

    //removes the first item in the list in constant time.
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("There is no element in this Deque");
        }
        Item item = first.item;
        first = first.next;
        size--;
        return item;
    }

    //removes the last item in the list in constant time.
    public Item removeLast() {
        if (isEmpty()) {
           throw new NoSuchElementException("There is no element in this Deque");
        }
        if (last == first) {
            removeFirst();
        } else {
            Item item = last.item;
            last = last.before;
            last.next = null;
            size--;
            return item;
        }
        return null;
    }

    @Override
    public Iterator<Item> iterator() {
        return new listIterator();
    }

    private class listIterator implements Iterator<Item> {

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("This method is unsupported");
        }
    }

    public static void main(String[] args) {
        Deque<String> link = new Deque<>();
        link.addFirst("a");
        link.addFirst("bb");
        link.addFirst("ccc");
        link.addFirst("dddd");
        link.addLast("bb");
        link.addLast("ccc");
        link.addLast("dddd");
        link.removeLast();
        link.removeLast();
        link.removeLast();

        for (String el : link) {
            StdOut.println(el);
        }
    }
}
