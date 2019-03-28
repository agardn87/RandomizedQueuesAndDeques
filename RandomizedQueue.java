package A02RandomizedQueuesAndDeques;

/**
 * Created by Adam Gardner and Cesar Gonzalez on 10/8/16.
 */

import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.introcs.StdOut;
import edu.princeton.cs.introcs.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int size = 0;
    private Item[] array;
    private int head;
    private int tail = 0;

    public RandomizedQueue(){
        // construct an empty randomized queue
    }

    // is the queue empty?
    public boolean isEmpty(){
        return size == 0;
    }

    // return the number of items on the queue
    public int size(){
        return size;
    }

    // add the item
    public void enqueue(Item item){
        if(item == null){ throw new NullPointerException(); }

        //init empty array
        if(isEmpty()){
            array = (Item[])(new Object[10]);
        }

        //Double array when full
        if(size == array.length){
            Item [] auxArray = (Item[])(new Object[size * 2]);
            for(int i = 0; i < size; i++){
                auxArray[i] = array[i];
            }
            array = auxArray;
        }
        size++;
        array[tail] = item;
        tail++;
    }

    // delete and return a random item
    public Item dequeue(){
        if(isEmpty()){ throw new NoSuchElementException(); }
        //half array when only 1/4 full
        if(size == array.length/4 ){
            Item [] auxArray = (Item[])(new Object[array.length /2]);
            for(int i = 0; i < size; i++){
                auxArray[i] = array[i];
            }
            array = auxArray;
        }
        Item second = sample();
        int location = tail - 1;
        array[head] = array[location];
        array[location] = null;
        --tail;
        --size;
        return second;
    }

    // return (but do not delete) a random item
    public Item sample(){
        if(isEmpty()){ throw new NoSuchElementException(); }
        head = StdRandom.uniform(tail);
        return array[head];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator(){
        return new RandomizedQueueIterator<Item>(array, tail);
    }

    public class RandomizedQueueIterator<Item> implements Iterator<Item>{
        Item[] current;
        int head = -1;
        int tail;

        public RandomizedQueueIterator(Item[] array,int end){
            current = array;
            tail = end;
        }

        public boolean hasNext(){
            return !(head == tail-1) ;
        }

        public Item next(){
            if(!hasNext()) { throw new NoSuchElementException(); }
            head++;
            return current[head];
        }

        public void remove(){
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args){
        // unit testing
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        queue.enqueue("one");
        queue.enqueue("two");
        queue.enqueue("three");
        queue.enqueue("four");
        queue.enqueue("five");

        //queue.dequeue();
        //queue.dequeue();
        StdOut.print("Dequeued: ");
        StdOut.print(queue.dequeue()  + ", ");
        StdOut.print( queue.dequeue() + ", ");
        StdOut.print( queue.dequeue() + " \n");


        StdOut.println();
        StdOut.print("Queue: ");
        for(String el: queue){
            StdOut.printf(el + ", ");
        }

    }
}