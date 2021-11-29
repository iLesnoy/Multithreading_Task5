package epam.task.multithreading.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

public class Ferry implements Runnable {

    private static final Logger logger = LogManager.getLogger();
    private static final List<Car> ferryCars = new ArrayList<>();
    private static final ConcurrentLinkedQueue<Car> carQueue = new ConcurrentLinkedQueue<>();
    private static final AtomicBoolean isInitialised = new AtomicBoolean(false);
    private static final ReentrantLock lock = new ReentrantLock();
    private final int MAX_LIFTING_CAPACITY = 15000;
    private final int MAX_AREA = 15000;
    private final AtomicLong weightCounter = new AtomicLong();
    private final AtomicLong sizeCounter = new AtomicLong();
    private final CountDownLatch latch = new CountDownLatch(1);
    private static Ferry instance;
    private boolean ferryIsSailing;

    public Ferry() {
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    public boolean ferryIsFloats() {
        return ferryIsSailing;
    }


    public static Ferry getInstance() {
        if (!isInitialised.get()) {
            try {
                lock.lock();
                if (instance == null) {
                    instance = new Ferry();
                    isInitialised.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }


    public void addCar(Car car) {

            latch.countDown();
            if (weightCounter.get() <= MAX_LIFTING_CAPACITY && sizeCounter.get() <= MAX_AREA) {
                car.setOnBoard(true);
                weightCounter.addAndGet(car.getCarWeight());
                sizeCounter.addAndGet(car.getCarSize());
                ferryCars.add(car);
                
            } else {
                carQueue.add(car);
            }
    }


    private void ferryUnload() {
        weightCounter.set(0);
        sizeCounter.set(0);
        ferryCars.clear();
    }


    @Override
    public void run() {
        while (carQueue.size() < 1) {
            logger.info("Ferry waiting for loading ");
            try {
                TimeUnit.SECONDS.sleep(2);
                if (ferryCars.size() >= 2) {
                    ferryIsSailing = true;
                    while (!ferryCars.isEmpty()) {
                        logger.info("The ferry delivers cars with a total weight " + weightCounter);
                        TimeUnit.SECONDS.sleep(2);
                        ferryUnload();
                        logger.info("The ferry deliver the cars");
                        latch.countDown();
                        TimeUnit.SECONDS.sleep(1);
                        ferryIsSailing = false;
                        latch.await();
                        TimeUnit.SECONDS.sleep(1);
                    }
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        logger.info("Cars in the queue " + ferryCars.size());
    }

}
