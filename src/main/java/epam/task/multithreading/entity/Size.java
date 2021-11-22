package epam.task.multithreading.entity;


public enum Size {
    PASSENGERS(randomCarSize()), CARGO(randomCarSize());
    
    int size;

    public double getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    Size(int size) {
        this.size = size;
    }

    static int randomCarSize() {
        return (int) (3000 + Math.random() * 400);
    }
}
