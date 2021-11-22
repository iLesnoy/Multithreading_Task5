package epam.task.multithreading.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.concurrent.Semaphore;

import static epam.task.multithreading.entity.Barge.count;

public class СarGenerator implements Runnable {

    private static final Logger logger = LogManager.getLogger();
    private final Barge barge;
    private final Semaphore sem;


    public СarGenerator(Barge barge, Semaphore s) {
        this.barge = barge;
        this.sem = s;
        new Thread(this).start();
    }


    @Override
    public void run() {

        try {
            sem.acquire();

            for (int i = 0; i < 10; i++) {
                logger.info("Car"+ count + " in queue for downloading");
                barge.add(new Car(getRandomSize(), getRandomWeight(), getRandomType()));
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /*System.out.println("Thread " + Thread.currentThread().getName() + " ended");*/
        sem.release();

    }


    private Weight getRandomWeight() {
        Random random = new Random();
        return Weight.values()[random.nextInt(Weight.values().length)];
    }

    private Size getRandomSize() {
        Random random = new Random();
        return Size.values()[random.nextInt(Size.values().length)];
    }

    private static Car.Type getRandomType() {
        Random random = new Random();
        return Car.Type.values()[random.nextInt(Car.Type.values().length)];
    }
}
