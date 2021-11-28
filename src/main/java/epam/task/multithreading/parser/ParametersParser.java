package epam.task.multithreading.parser;

import epam.task.multithreading.entity.Car;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ParametersParser {

    private final String DELIMITER_REGEX = ";";

    public Stream<Car> carParametersParse(List<String> cars){
        return  cars.stream()
                .map(a -> Stream.of(a.split(DELIMITER_REGEX)).map(String::toString).toList())
                .map(b -> new Car(b.get(0),b.get(1),Long.parseLong(b.get(2)),Long.parseLong(b.get(3)),
                        Arrays.stream(b.get(3).split(DELIMITER_REGEX))
                                .map(Long::parseLong)
                                .toList()));
    }
}
