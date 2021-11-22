package epam.task.multithreading.entity;


public class Car {
    private Size carSize;
    private Weight carWeight;
    private Type type;

    public enum Type{
        PASSENGERS,CARGO;
    }

    public Car(Size carSize, Weight carWeight,Type type) {
        this.carSize = carSize;
        this.carWeight = carWeight;
        this.type = type;
    }


    public double getCarSize() {
        return carSize.getSize();
    }

    public double getCarWeight() {
        return carWeight.getSize();
    }

    public Type getType() {
        return type;
    }
}

