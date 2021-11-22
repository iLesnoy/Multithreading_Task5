package epam.task.multithreading;

import epam.task.multithreading.entity.*;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {


        Barge barge = new Barge();

        Semaphore semaphore = new Semaphore(1,true);

        СarGenerator сarGenerator = new СarGenerator(barge,semaphore);
        Loader car1 = new Loader(barge,Car.Type.CARGO, Weight.CARGO, Size.CARGO,semaphore);
        Loader car2 = new Loader(barge,Car.Type.PASSENGERS, Weight.PASSENGERS, Size.PASSENGERS,semaphore);
        Loader car3 = new Loader(barge,Car.Type.CARGO, Weight.CARGO, Size.CARGO,semaphore);
        Loader car4 = new Loader(barge,Car.Type.CARGO, Weight.CARGO, Size.CARGO,semaphore);
        Loader car5 = new Loader(barge,Car.Type.PASSENGERS, Weight.PASSENGERS, Size.PASSENGERS,semaphore);
        Loader car6 = new Loader(barge,Car.Type.PASSENGERS, Weight.PASSENGERS, Size.PASSENGERS,semaphore);
        Loader car7 = new Loader(barge,Car.Type.PASSENGERS, Weight.PASSENGERS, Size.PASSENGERS,semaphore);









    }



}
