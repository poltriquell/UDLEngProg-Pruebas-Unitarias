package citizenmanagementplatform;

import Controller.Citizen;
import data.DocPath;
import data.Nif;
import data.SmallCode;
import data.Goal;
import publicadministration.CreditCard;

import services.exceptions.*;
import java.util.Date;

public interface UnifiedPlatformInterface {
    // Input events
    void selectJusMin ();
    void selectProcedures ();
    void selectCriminalReportCertf ();
    void selectAuthMethod (byte opc);

    void enterNIFandPINobt (Nif nif, Date valDate) throws NifNotRegisteredException, IncorrectValDateException, AnyMobileRegisteredException, ConnectException;
    void enterPIN (SmallCode pin) throws NotValidPINException,ConnectException;
    void enterForm (Citizen citizen, Goal goal) throws IncompleteFormException, IncorrectVerificationException, ConnectException;
    void enterCred (Nif nif, Password passw) throws NifNotRegisteredException, NotValidCredException, AnyMobileRegisteredException, ConnectException;
    void realizePayment ();
    void enterCardData (CreditCard cardD) throws IncompleteFormException, NotValidPaymentDataException, InsufficientBalanceException, ConnectException;
    void obtainCertificate () throws BadPathException, DigitalSignatureException, ConnectException;

    void printDocument () throws BadPathException, PrintingException;

    // The setter methods for injecting the dependences
    // Other input events (not required)
    // Other internal operations (not required)

    void registerPayment ();
    void openDocument (DocPath path) throws BadPathException;
    void printDocument (DocPath path) throws BadPathException, PrintingException;
}
