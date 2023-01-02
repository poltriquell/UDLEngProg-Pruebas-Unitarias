package services;

import services.exceptions.*;

import java.math.BigDecimal;

public interface CAS {      // External service that represents theCredit Authorization Service
    void askForApproval(String nTrans, String cardData, String date, BigDecimal imp)
            throws NotValidPaymentDataException, InsufficientBalanceException, ConnectException;
}
