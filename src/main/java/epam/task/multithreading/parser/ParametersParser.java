package epam.task.multithreading.parser;

import epam.task.multithreading.entity.Car;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;


public class ParametersParser {

    private static final String DELIM_REGEX = "\\(([\\w\\;\\d]+)\\)";
    private final String DELIMITER_REGEX = ";";
    private final List<String> finalParam = new ArrayList<>();


    public List<String> mainData(List<String>carParam) {

        String results = String.join("",carParam);
        Pattern pattern = Pattern.compile(DELIM_REGEX);
        Matcher matcher = pattern.matcher(results);

        while (matcher.find()){
            finalParam.add(matcher.group(1));
        }
        return finalParam;
    }


    public Stream<Car> carParameters(List<String>cars){
        return  cars.stream()
                .map(a -> Stream.of(a.split(DELIMITER_REGEX)).map(String::toString).toList())
                .map(b -> new Car(b.get(0),b.get(1),Long.parseLong(b.get(2)),Long.parseLong(b.get(3)),
                        Arrays.stream(b.get(3).split(DELIMITER_REGEX))
                                .map(Long::parseLong)
                                .toList()));
    }

}


