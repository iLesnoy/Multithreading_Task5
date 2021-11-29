package epam.task.multithreading.entity;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


public class Car implements Runnable {
    private static final Logger logger = LogManager.getLogger();
    private final long carSize;
    private final long carWeight;
    private final String type;
    private final String name;
    private Ferry ferry;
    private boolean onBoard;



    public Car(String type, String name, long carWeight, long carSize, List<Long> params) {
        this.type = type;
        this.name = name;
        this.carWeight = carWeight;
        this.carSize = carSize;
    }

    public long getCarSize() {
        return carSize;
    }

    public long getCarWeight() {
        return carWeight;
    }

    public String getName() {
        return name;
    }

    public void setOnBoard(boolean onBoard) {
        this.onBoard = onBoard;
    }

    public void setFerry(Ferry ferry) {
        this.ferry = ferry;
    }

    private void loadOnFerry(){
        while (!onBoard){
            try{
                if(ferry.ferryIsFloats()){
                    TimeUnit.SECONDS.sleep(1);
                }else {
                        ferry.addCar(this);
                        ferry.getLatch().await(); //ferry waiting for loading
                        logger.warn("Car "+ this.type + " " + this.getName()+ " drove to the barge");
                        TimeUnit.SECONDS.sleep(5);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        logger.info("Car " + this.type+ " ready for loading");
        loadOnFerry();
        logger.info("Car " + this.type+ " left the barge");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return carSize == car.carSize &&
                carWeight == car.carWeight &&
                Objects.equals(type, car.type) && Objects.equals(name, car.name);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (int)(carWeight- (carWeight >>> 32));
        result = 31 * result + (int)(carSize- (carSize >>> 32));
        result = 31 * result + (name == null? 0 : name.hashCode());
        result = 31 * result + (onBoard ? 1:0);
        return result;
    }
}

