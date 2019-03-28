package A02RandomizedQueuesAndDeques;

/**
 * Created by Adam Gardner and Cesar Gonzalez on 10/8/16.
 */
        import edu.princeton.cs.introcs.StdIn;
        import edu.princeton.cs.introcs.StdOut;

public class Subset {

    public static void main(String[] args){
        RandomizedQueue<String> randomQueue = new RandomizedQueue<>();
        boolean exit = false;
        String string;
        StdOut.print("Enter a number: ");
        int k = StdIn.readInt();

        //read line of Strings
        StdOut.println("Enter items to be placed in an array Enter 0 to Exit: ");

        while(!exit){
            string = StdIn.readString();
            if(string.equals("0"))
                exit = true;
            else
                randomQueue.enqueue(string);
        }

        StdOut.println();
        StdOut.print("% echo ");
        for(String el: randomQueue){
            StdOut.print(el + " ");
        }

        StdOut.print("| java SubSet " + k);
        StdOut.println();
        for(int i = 0; i < k; i++){
            StdOut.println(randomQueue.dequeue() + " ");
        }
    }
}