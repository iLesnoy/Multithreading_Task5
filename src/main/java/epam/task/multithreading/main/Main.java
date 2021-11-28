package epam.task.multithreading.main;

import epam.task.multithreading.entity.*;
import epam.task.multithreading.exception.ReaderException;
import epam.task.multithreading.parser.MainDataParser;
import epam.task.multithreading.parser.ParametersParser;
import epam.task.multithreading.reader.ParametersReader;


import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws ReaderException {

        List<String> carType = ParametersReader.readData("resources/data.txt");
        ParametersParser ParametersParser = new ParametersParser();


        List<String> list = MainDataParser.mainDataParser(carType);
        Stream<Car> list1 = ParametersParser.carParametersParse(list);
        List<Car> result = list1.collect(Collectors.toList());
        Ferry ferry = new Ferry();


        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(ferry);

        for (Car car : result) {
            car.setFerry(ferry);
            executorService.submit(car);
        }

        executorService.shutdown();

    }
}


