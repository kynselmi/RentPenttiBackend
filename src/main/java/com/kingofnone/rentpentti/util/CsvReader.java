package com.kingofnone.rentpentti.util;

import com.kingofnone.rentpentti.service.impl.PaymentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class PaymentImporter {

    private static final Logger logger = LogManager.getLogger(PaymentImporter.class);

    @Value("${payment.csv.delimiter}")
    private String delimiter;

    @Autowired
    private PaymentService paymentService;

    public void importPayments(String path) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String row;
            while ((row = reader.readLine()) != null) {
                String[] data = row.split(delimiter);
            }
        } catch (FileNotFoundException e) {
            logger.error("CSV file not found at path: "+path, e);
        } catch (IOException e) {
            logger.error("CSV reading failed for path: "+path, e);
        }

    }
}
