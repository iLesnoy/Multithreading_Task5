package epam.task.multithreading.entity;

import java.util.concurrent.Semaphore;

import static epam.task.multithreading.entity.Barge.count;


public class Loader implements Runnable {

    private Barge barge;
    private Car.Type carType;
    private Weight weight;
    private Size size;
    private Semaphore semaphore;

    public Loader() {
    }


    public Loader(Barge barge, Car.Type carType, Weight weight, Size size, Semaphore sem) {
        this.barge = barge;
        this.carType = carType;
        this.weight = weight;
        this.size = size;
        this.semaphore = sem;
        new Thread(this).start();
    }


    @Override
    public void run() {

        try {

            Thread.currentThread().setName(carType.name());
            semaphore.acquire();

            for (int i = 0; i < count; i++) {
                barge.get(carType);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        semaphore.release();
        /*System.out.println(Thread.currentThread().getName()+ " end");*/
    }
}
