package epam.task.multithreading.entity;

public enum Weight {

    PASSENGERS(randomPassWeight()), CARGO(randomCargoWeight());

    int size;

    public int getSize() {
        return size;
    }

    Weight(int size) {
        this.size = size;
    }

    static int randomPassWeight() {
        return (int) (1700 + Math.random() * 400);
    }

    static int randomCargoWeight() {
        return (int) (2200 + Math.random() * 500);
    }
}
