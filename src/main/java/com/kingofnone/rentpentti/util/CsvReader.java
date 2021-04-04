package com.kingofnone.rentpentti.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Stream;

@Component
public class CsvReader {

    private static final Logger logger = LogManager.getLogger(CsvReader.class);

    @Value("${payment.csv.delimiter}")
    private String delimiter;

    public Stream<String[]> readCsvAsStream(String path) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            return reader.lines().map( line -> line.split(delimiter));
        } catch (FileNotFoundException e) {
            logger.error("CSV file not found at path: "+path, e);
        } catch (IOException e) {
            logger.error("CSV reading failed for path: "+path, e);
        }

        return Stream.empty();
    }

    public String getDelimiter() {
        return delimiter;
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }
}
