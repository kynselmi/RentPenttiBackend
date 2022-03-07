package com.kingofnone.rentpentti.integration.payment;

import com.kingofnone.rentpentti.model.Payment;
import com.kingofnone.rentpentti.model.PaymentParty;
import com.kingofnone.rentpentti.model.PaymentRecord;
import com.kingofnone.rentpentti.model.factory.impl.FactoryService;
import com.kingofnone.rentpentti.service.model.impl.PaymentModelService;
import com.kingofnone.rentpentti.service.model.impl.PaymentPartyModelService;
import com.kingofnone.rentpentti.service.model.impl.PaymentRecordModelService;
import com.kingofnone.rentpentti.util.CsvReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
/**
 * Imports bank payments from a CSV file
 */
public class PaymentImporter {

    private static final Logger logger = LogManager.getLogger(PaymentImporter.class);

    @Autowired
    private PaymentModelService paymentService;
    @Autowired
    private PaymentPartyModelService paymentPartyService;
    @Autowired
    private PaymentRecordModelService paymentRecordService;
    @Autowired
    private CsvReader csvReader;

    /**
     * Imports bank payments from CSV file to the database. CSV file may contain header which is skipped with
     * headerIncluded parameter. Importing will be cancelled and an exception is thrown if there are problems
     * in parsing the CSV
     * @param file import file as CSV
     * @param receiver name of the payment receiver
     * @param headerIncluded does CSV file include header
     * @throws PaymentImportException when any exceptions arise with parsing
     */
    public void importPayments(File file, String receiver, Boolean headerIncluded) throws PaymentImportException {
        Stream<String[]> csvLines = csvReader.readCsvAsStream(file.getPath());
        // Skip header row
        if (headerIncluded) {
            csvLines = csvLines.skip(1);
        }

        List<BankPayment> bankPaymentList = new ArrayList<>();
        for (String[] line : csvLines.collect(Collectors.toList())) {
            try {
                bankPaymentList.add(createPayment(line));
            } catch (ParseException e) {
                logger.error("Parsing payment data array failed for payment: "+Arrays.toString(line), e);
                throw new PaymentImportException();
            }
        }

        for (BankPayment bankPayment : bankPaymentList) {
            Payment newPayment = (Payment) FactoryService.getFactory(Payment.class).create();

            // Set payer to existing PaymentParty if found, otherwise create new one
            setPaymentPartyForPayment(newPayment, bankPayment.getPayerName());

            // Set receiver to existing PaymentParty if found, otherwise create a new one
            setPaymentPartyForPayment(newPayment, receiver);

            newPayment.setAmount(bankPayment.getAmount());
            Optional<Payment> savedPayment = paymentService.create(newPayment);
            if (savedPayment.isEmpty()) {
                logger.info("Payment was already created, skipping");
                continue;
            }

            // Create payment record
            createPaymentRecord(bankPayment.getTransactionDate(), newPayment);


            logger.info("Payment record created for payment: "+savedPayment.get().getId());
        }
    }

    /**
     * Set payment party for payment. Payment party is fetched from database by name. If no payment party is found
     * a new one will be created
     * @param payment payment that payment party is added to
     * @param partyName payment party name
     */
    private void setPaymentPartyForPayment(Payment payment, String partyName) {
        Optional<PaymentParty> paymentPartyOptional = paymentPartyService.getByName(partyName);
        PaymentParty paymentParty;
        if (paymentPartyOptional.isPresent()) {
            paymentParty = paymentPartyOptional.get();
            logger.info("Found payer PaymentParty in the database for: "+paymentParty.getName());
        } else {
            paymentParty = createPaymentParty(partyName);
            logger.info("Created new PaymentParty with name: "+paymentParty.getName());
        }
        payment.setReceiver(paymentParty);
    }

    /**
     * Creates a payment party
     * @param name payment party name
     * @return created payment party
     */
    private PaymentParty createPaymentParty(String name) {
        PaymentParty paymentParty = (PaymentParty) FactoryService.getFactory(PaymentParty.class).create();
        paymentParty.setName(name);
        paymentPartyService.create(paymentParty);
        return paymentParty;
    }

    /**
     * Creates a payment record and adds it to payment
     * @param date payment record date
     * @param payment payment where payment record is saved to
     * @return created payment record
     */
    private PaymentRecord createPaymentRecord(Date date, Payment payment) {
        PaymentRecord paymentRecord = (PaymentRecord) FactoryService.getFactory(PaymentRecord.class).create();
        paymentRecord.setPayment(payment);
        paymentRecord.setPayedDate(date);
        paymentRecordService.create(paymentRecord);
        return paymentRecord;
    }

    /**
     * Creates bank payment DTO from payment data
     * @param paymentData CSV line as String array
     * @return bank payment DTO
     * @throws ParseException
     */
    public BankPayment createPayment(String[] paymentData) throws ParseException {
        BankPayment bankPayment = new BankPayment();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        bankPayment.setTransactionDate(dateFormat.parse(paymentData[0]));
        bankPayment.setAmount(BigDecimal.valueOf(Long.parseLong(paymentData[1].replace(",", "."))));
        bankPayment.setPayerName(paymentData[4]);
        bankPayment.setReferenceNumber(paymentData[6]);
        return bankPayment;
    }

    public PaymentModelService getPaymentService() {
        return paymentService;
    }

    public void setPaymentService(PaymentModelService paymentService) {
        this.paymentService = paymentService;
    }

    public PaymentPartyModelService getPaymentPartyService() {
        return paymentPartyService;
    }

    public void setPaymentPartyService(PaymentPartyModelService paymentPartyService) {
        this.paymentPartyService = paymentPartyService;
    }

    public CsvReader getCsvReader() {
        return csvReader;
    }

    public void setCsvReader(CsvReader csvReader) {
        this.csvReader = csvReader;
    }

    public PaymentRecordModelService getPaymentRecordService() {
        return paymentRecordService;
    }

    public void setPaymentRecordService(PaymentRecordModelService paymentRecordService) {
        this.paymentRecordService = paymentRecordService;
    }
}
