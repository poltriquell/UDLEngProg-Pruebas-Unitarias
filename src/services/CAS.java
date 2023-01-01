package services;

import services.exceptions.*;
import publicadministration.*;

import java.math.BigDecimal;
import java.util.Date;

public interface CAS {      // External service that represents theCredit Authorization Service
    void askForApproval(String nTrans, String cardData, String date, BigDecimal imp)
            throws NotValidPaymentDataException, InsufficientBalanceException, ConnectException;
}
