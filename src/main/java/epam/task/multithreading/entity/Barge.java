package epam.task.multithreading.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Barge {

    private static final Logger logger = LogManager.getLogger();
    public  static final List<Car> bargeCars = new ArrayList<>();
    private final ConcurrentLinkedQueue<Car> carQueue = new ConcurrentLinkedQueue<>();
    private final int MAX_LIFTING_CAPACITY = 20000;
    private final int MAX_AREA = 20000;
    private final int MIN_LIFTING_CAPACITY = 0;
    private final int MIN_AREA = 0;
    private int weightCounter = 0;
    private int sizeCounter = 0;
    public static int count;


    public void add(int count) {
        Barge.count = count;
    }

    public boolean add(Car car) {
        try {
            if (weightCounter <= MAX_LIFTING_CAPACITY && sizeCounter <= MAX_AREA) {

                bargeCars.add(car);
                weightCounter += car.getCarWeight();
                sizeCounter += car.getCarSize();
                count++;
                logger.info("car " + car.getType() + " was loaded into the platform: " + Thread.currentThread().getName() + car.getCarWeight() + " " + car.getCarSize());


            } else {
                logger.warn("not enough storage or space  " + " Weight " + weightCounter + " Size " + sizeCounter);
                carQueue.add(car);
                logger.info("Cars in the queue " + carQueue.size());

                return false;
            }
        } finally {

        }
        return true;
    }


    public Car get(Car.Type type) throws InterruptedException {
        try {
            if (weightCounter >= MIN_LIFTING_CAPACITY || sizeCounter >= MIN_AREA) {

                for (Car car : bargeCars) {
                    if (car.getType() == type) {
                        weightCounter -= car.getCarWeight();
                        sizeCounter -= car.getCarSize();
                        bargeCars.remove(car);

                        count--;
                        return car;
                    }
                }
            }

        } finally {

        }
        return null;
    }

}
