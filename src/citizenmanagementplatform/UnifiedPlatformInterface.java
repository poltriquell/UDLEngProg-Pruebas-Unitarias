package citizenmanagementplatform;

import citizenmanagementplatform.exceptions.BadPathException;
import citizenmanagementplatform.exceptions.IncompleteFormException;
import citizenmanagementplatform.exceptions.PrintingException;
import citizenmanagementplatform.exceptions.ProceduralException;
import exceptions.WrongNifFormatException;
import publicadministration.Citizen;
import data.DocPath;
import data.Nif;
import data.Password;
import data.SmallCode;
import data.Goal;
import publicadministration.CreditCard;

import publicadministration.exceptions.DigitalSignatureException;
import publicadministration.exceptions.WrongMobileFormatException;
import services.exceptions.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

public interface UnifiedPlatformInterface {
    // Input events
    void selectJusMin ();
    void selectProcedures();
    void selectCriminalReportCertf ();
    void selectAuthMethod (byte opc);

    void enterNIFandPINobt (Nif nif, LocalDate valDate) throws NifNotRegisteredException, IncorrectValDateException, AnyMobileRegisteredException, ConnectException, NotValidCredException, WrongMobileFormatException, WrongNifFormatException, ProceduralException;
    void enterPIN (SmallCode pin) throws NotValidPINException, ConnectException, IOException, DigitalSignatureException, ProceduralException;
    void enterForm (Citizen citizen, Goal goal) throws IncompleteFormException, IncorrectVerificationException, ConnectException, ProceduralException;
    void enterCred (Nif nif, Password passw) throws NifNotRegisteredException, NotValidCredException, AnyMobileRegisteredException, ConnectException;
    void realizePayment ();
    void enterCardData (CreditCard cardD) throws IncompleteFormException, NotValidPaymentDataException, InsufficientBalanceException, ConnectException, ProceduralException;
    void obtainCertificate () throws BadPathException, DigitalSignatureException, ConnectException, ProceduralException;
    void printDocument () throws BadPathException, PrintingException;

    // The setter methods for injecting the dependences
    // Other input events (not required)
    // Other internal operations (not required)

    void registerPayment ();
    void openDocument (DocPath path) throws BadPathException;
    void printDocument (DocPath path) throws BadPathException, PrintingException;
}