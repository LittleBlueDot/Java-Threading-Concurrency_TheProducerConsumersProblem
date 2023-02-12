public class Main {

    public static void main(String[] args) {
        Container<Integer> queue = new Container<>(10);

        Producer p1 = new Producer(queue, 15);
        Thread t1 = new Thread(p1);


        Consumer c1 = new Consumer(queue, 7);
        Thread t2 = new Thread(c1);

        t2.start();
        t1.start();


    }
}
