package com.kingofnone.rentpentti.util;

import com.kingofnone.rentpentti.service.impl.PaymentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Stream;

public class PaymentImporter {

    private static final Logger logger = LogManager.getLogger(PaymentImporter.class);

    @Autowired
    private PaymentService paymentService;
    @Autowired
    private CsvReader csvReader;

    public void importPayments(String filePath) {
        Stream<String> dataStream = csvReader.readCsvAsStream(filePath);
        dataStream.map( data -> data.split())
    }
}
