public class Producer implements Runnable {

    private Container container;

    private int platesToProduce;

    private int preparedPlates;

    public Producer(Container container, int platesToProduce) {
        this.container = container;
        this.platesToProduce = platesToProduce;
    }

    @Override
    public void run() {
        while (preparedPlates < platesToProduce) {
            synchronized (container) {
                container.fillContainer(preparedPlates + 1);

                preparedPlates++;

                System.out.println(container + " - " + Thread.currentThread().getName() + ". The chef prepared the plate of food.");
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("The chef has finished his job!");
    }

}
