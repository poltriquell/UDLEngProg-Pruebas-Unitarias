package citizenmanagementplatform;

import Controller.Citizen;
import data.DocPath;
import data.Nif;
import data.SmallCode;
import data.goalTypes;
import services.CertificationAuthority;
import services.JusticeMinistry;
import services.exceptions.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class UnifiedPlatform implements UnifiedPlatformInterface {
    Citizen citizen;
    CertificationAuthority authMethod;
    public JusticeMinistry justiceMinistry;

    public ArrayList<String> possibleAuthenticationMethods;

    // Input events
    public void selectJusMin () {

    }
    public void selectProcedures () {

    }
    public void selectCriminalReportCertf () {

    }
    public void selectAuthMethod (byte opc) {
        // Assuming that Authentication methods are stored in the same order as they are displayed to the user in the GUI
        String selectedAuthenticationMethod = possibleAuthenticationMethods.get(opc - 1);
        System.out.println("Se ha seleccionado el siguiente método de autenticación : " + selectedAuthenticationMethod);
    }

    public void enterNIFandPINobt (Nif nif, Date valDate) throws  NifNotRegisteredException, IncorrectValDateException, AnyMobileRegisteredException, ConnectException {
        // Assuming auth method is Cl@ve PIN
        citizen.setNif(nif);  // We set the citizen nif to the one we got through parameter
        citizen.setValDate(valDate);  // We set the citizen validation date to the one we got through parameter
        if (authMethod.sendPIN(nif, valDate)) {
            System.out.println("Se envia el PIN al usuario con DNI -> " + nif.getNif());
        } else {
            throw new ConnectException("Ha ocurrido un error al enviar el PIN al número de teléfono móvil correspondiente.");
        }
    }
    public void enterPIN (SmallCode pin) throws NotValidPINException,ConnectException {
        if (authMethod.checkPIN(citizen.getNif(), pin)) {
            System.out.println("El PIN introducido es correcto y se corresponde con el generado por el sistema previamente. Se indica al usuario de su vigencia.");
            if (justiceMinistry != null) {
                PDFDocument pdf = justiceMinistry.getCriminalRecordCertf(citizen, Goal.CriminalRecordCertf); // Goal ???
                citizen.setPDFDocument(pdf);
                pdf.openDoc(pdf.getPath());
                System.out.println("Se procede a mostrar el certificado de antecedentes penales.");
            } else {
                System.out.println("No se ha seleccionado ningún ministerio de justicia.");
            }
        } else {
            throw new NotValidPINException("El PIN introducido no es correcto y no se corresponde con el generado por el sistema previamente. Se indica al usuario que podria no estar vigente.");
        }
    }
    private  void enterForm (Citizen citz, Goal goal) throws IncompleteFormException, IncorrectVerificationException, ConnectException {

    }
    private  void realizePayment () {

    }
    private  void enterCardData (CreditCard cardD) throws IncompleteFormException, NotValidPaymentDataException, InsufficientBalanceException, ConnectException {

    }
    private  void obtainCertificate () throws BadPathException, DigitalSignatureException, ConnectException {

    }

    private  void printDocument () throws BadPathException, PrintingException {
        printDocument(citizen.getPDFDocument().getPath());
    }

    // The setter methods for injecting the dependences
    // Other input events (not required)
    // Other internal operations (not required)

    private  void registerPayment () {

    }
    private  void openDocument (DocPath path) throws BadPathException {
        try {
            citizen.getPDFDocument().openDoc(path);
        } catch (IOException e) {
            throw new BadPathException("El documento no se ha podido abrir debido a que el path no es correcto.");
        }
    }
    private  void printDocument (DocPath path)  throws BadPathException, PrintingException {
        if (!new File(path.getDocPath()).exists()) throw new BadPathException("El documento no se ha podido imprimir debido a que el path no es correcto.");
        System.out.println("El documento se ha enviado de forma correcta para su impresión.");
    }
}
