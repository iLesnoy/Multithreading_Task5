package epam.task.multithreading.main;

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


        ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        /*service.execute(сarGenerator);*/
        service.execute(car1);
        service.execute(car2);
        service.execute(car3);
        service.execute(car4);
        service.execute(car5);
        service.execute(car6);
        service.execute(car7);
        service.shutdown();



    }



}
