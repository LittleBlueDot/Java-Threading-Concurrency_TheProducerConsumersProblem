import java.util.Deque;
import java.util.LinkedList;

public class Container<T> {
    private Deque<T> queue;

    private int limitOfPlates;

    public Container(int limitOfPlates) {
        queue = new LinkedList<>();
        this.limitOfPlates = limitOfPlates;
    }

    public void fillContainer(T item) {
        while (queue.size() == limitOfPlates) {
            try {
                wait(); // IF THE CONTAINER IS FULL, THIS THREAD WILL WAIT UNTIL AN ITEM IS REMOVED
            } catch (InterruptedException e) {
            }
        }
        queue.add(item);
        notifyAll(); // AN ITEM WAS INTRODUCED, SO WE SHOULD NOTIFY THE THREADS THAT ARE WAITING FOR CONSUMPTION
    }

    public T removeFromContainer() {
        while (queue.size() == 0) {
            System.out.println("The container is empty. The consumer is waiting until the chef will prepare food");
            try {
                wait(); // IF THE CONTAINER IS EMPTY, THIS THREAD WILL WAIT UNTIL AN ITEM IS PRODUCED
            } catch (InterruptedException e) {
                // thread.interrupt called, no handling needed
            }
        }

        T item = queue.removeFirst();

        notifyAll(); // AN ITEM WAS CONSUMED, SO WE SHOULD NOTIFY THE THREADS THAT ARE WAITING FOR PRODUCTION

        return item;

    }

    @Override
    public String toString() {
        return "Container {" +
                "queue=" + queue +
                '}';
    }
}
