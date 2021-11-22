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

    public void setCarSize(Size carSize) {
        this.carSize = carSize;
    }

    public double getCarWeight() {
        return carWeight.getSize();
    }

    public void setCarWeight(Weight carWeight) {
        this.carWeight = carWeight;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}

