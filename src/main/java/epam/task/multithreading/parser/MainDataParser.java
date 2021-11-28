package epam.task.multithreading.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainDataParser {

    private static final String DELIM_REGEX = "\\(([\\w\\;\\d]+)\\)";
    private static final String SPACE_DELIM = "\\s+";
    private static final List<String> finalParam = new ArrayList<>();


    public static List<String> mainDataParser(List<String> carParam) {

            String results = String.join(SPACE_DELIM,carParam);
            Pattern pattern = Pattern.compile(DELIM_REGEX);
            Matcher matcher = pattern.matcher(results);

            while (matcher.find()){
                finalParam.add(matcher.group(1));
            }
            return finalParam;
        }
}


