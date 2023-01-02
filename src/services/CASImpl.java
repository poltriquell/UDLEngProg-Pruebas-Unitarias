package services;

import publicadministration.CreditCard;
import services.exceptions.ConnectException;
import services.exceptions.InsufficientBalanceException;
import services.exceptions.NotValidPaymentDataException;

import java.math.BigDecimal;
import java.util.Date;

public class CASImpl implements CAS{

    @Override
    public void askForApproval(String nTrans, String cardData, String date, BigDecimal imp) throws NotValidPaymentDataException, InsufficientBalanceException, ConnectException {
        System.out.println("\033[32mVerificando los datos de la tarjeta de crédito...\033[0m");
        //Make a pause to simulate the verification process
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("\033[32mTransacción "+ nTrans + " en curso...\033[0m");
        carryTrans(cardData,date,imp);
    }

    private void carryTrans(String cardData, String date, BigDecimal imp) throws NotValidPaymentDataException, InsufficientBalanceException, ConnectException {
        System.out.println("\033[32mRealizando la transacción...\033[0m");
        //Make a pause to simulate the verification process
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System. out .println("____________________________________________");
        //Imprime el recibo con los datos de la transacción
        System.out.println("\033[32mRecibo de la transacción:\033[0m");
        System.out.println("Número de tarjeta: " + cardData);
        System.out.println("Importe de la transacción: " + imp);
        System.out.println("____________________________________________");
    }
}
