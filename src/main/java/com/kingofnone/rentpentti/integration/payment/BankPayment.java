package com.kingofnone.rentpentti.integration.payment;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Objectification of a Nordea bank payment. Bank payments can be exported from Nordea netbank as CSV. The file
 * includes all payments from selected time frame. This class represents one line of the CSV.
 */
public class BankPayment {

    private Date transactionDate;
    private BigDecimal amount;
    private String payerIBAN;
    private String receiverIBAN;
    private String payerName;
    private String title;
    private String referenceNumber;
    private String balance;
    private String currency;

    public BankPayment() {}

    public BankPayment(Date transactionDate, BigDecimal amount, String payerIBAN, String receiverIBAN,
                       String payerName, String title, String referenceNumber, String balance, String currency) {
        this.transactionDate = transactionDate;
        this.amount = amount;
        this.payerIBAN = payerIBAN;
        this.receiverIBAN = receiverIBAN;
        this.payerName = payerName;
        this.title = title;
        this.referenceNumber = referenceNumber;
        this.balance = balance;
        this.currency = currency;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPayerIBAN() {
        return payerIBAN;
    }

    public void setPayerIBAN(String payerIBAN) {
        this.payerIBAN = payerIBAN;
    }

    public String getReceiverIBAN() {
        return receiverIBAN;
    }

    public void setReceiverIBAN(String receiverIBAN) {
        this.receiverIBAN = receiverIBAN;
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return """
                Transaction Date: %s
                Amount: %s
                Payer IBAN: %s
                Receiver IBAN: %s
                PayerName: %s
                Title: %s
                Reference Number: %s
                Balance: %s
                Currency: %s
                """.formatted(amount, payerIBAN, receiverIBAN, payerName, title, referenceNumber, balance, currency);
    }
}
