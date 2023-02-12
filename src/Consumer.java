public class Consumer implements Runnable {

    private Container container;

    private int platesToConsume;
    private int consumedPlates;

    public Consumer(Container container, int platesToConsume) {
        this.container = container;
        this.platesToConsume = platesToConsume;
    }

    @Override
    public void run() {
        while (consumedPlates < platesToConsume) {
            synchronized (container) {
                container.removeFromContainer();
                System.out.println(container + " - " + Thread.currentThread().getName() + ". The consumer has taken the plate from the container.");

                consumedPlates++;

            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("The consumer has finished his job!");
    }

}

