package epam.task.multithreading.reader;

import epam.task.multithreading.exception.ReaderException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;


public class ParametersReader {
    private static final Logger logger = LogManager.getLogger();
    private static final String DEFAULT_FILENAME = "resources/data.txt.txt";

    public static List<String> readData(String filepath) throws ReaderException {
        List<String> list;
        Path path = Paths.get(filepath);
        try {

            list = Files.lines(path).collect(Collectors.toList());

        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
            throw new ReaderException("File not found " + filepath,e);
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new ReaderException(e.getMessage());
        }
        return list;
    }
}
