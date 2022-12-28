package citizenmanagementplatform;

import Controller.Citizen;
import data.DocPath;
import data.Nif;
import data.Password;
import data.SmallCode;
import publicadministration.exceptions.DigitalSignatureException;
import services.CertificationAuthority;
import services.JusticeMinistry;
import services.exceptions.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class UnifiedPlatform implements UnifiedPlatformInterface {
    Citizen citz;
    CertificationAuthority authMethod;
    public JusticeMinistry justiceMinistry;

    public ArrayList<String> possibleAuthenticationMethods;

    public UnifiedPlatform() {
        this.citz = new Citizen();
        //this.aapp = new HashMap<>();
        //setAAPP();

        //this.services = new HashMap<>();
        //setServices();

        this.possibleAuthenticationMethods = new ArrayList<>();
        setAuthenticationMethods();

        //this.possibleDigitalCertificates = new ArrayList<>();
        //setDigitalCertificates();

        //setEncryptingKeys();
    }

    private void setAuthenticationMethods() {
        //We could use more authentication methods, but for the sake of simplicity we will use only one.
        possibleAuthenticationMethods.add("Cl@ve PIN");
        possibleAuthenticationMethods.add("Cl@ve Permanente");
        possibleAuthenticationMethods.add("Certificado digital");
    }

    // Input events
    public void selectJusMin () {
        System.out.println("Se ha hecho click en la sección  Ministerio de Justicia en el mosaico inicial.");
    }
    public void selectProcedures () {
        System.out.println("Se ha hecho click en el enlace \"Trámites\" de la sección de la SS.");
    }
    public void selectCriminalReportCertf () {
        System.out.println("Se ha seleccionado el trámite \"Obtener el certificado de antecedentes penales\".");
    }
    public void selectAuthMethod (byte opc) {
        // Assuming that Authentication methods are stored in the same order as they are displayed to the user in the GUI
        String selectedAuthenticationMethod = possibleAuthenticationMethods.get(opc - 1);
        System.out.println("Se ha seleccionado el siguiente método de autenticación : " + selectedAuthenticationMethod);
    }

    public void enterNIFandPINobt (Nif nif, Date valDate) throws  NifNotRegisteredException, IncorrectValDateException, AnyMobileRegisteredException, ConnectException {
        // Assuming auth method is Cl@ve PIN
        citz.setNif(nif);  // We set the citizen nif to the one we got through parameter
        citz.setValDate(valDate);  // We set the citizen validation date to the one we got through parameter
        if (authMethod.sendPIN(nif, valDate)) {
            System.out.println("Se envia el PIN al usuario con DNI -> " + nif.getNif());
        } else {
            throw new ConnectException("Ha ocurrido un error al enviar el PIN al número de teléfono móvil correspondiente.");
        }
    }
    public void enterPIN (SmallCode pin) throws NotValidPINException,ConnectException {
        if (authMethod.checkPIN(citz.getNif(), pin)) {
            System.out.println("El PIN introducido es correcto y se corresponde con el generado por el sistema previamente. Se indica al usuario de su vigencia.");
            if (justiceMinistry != null) {
                PDFDocument pdf = justiceMinistry.getCriminalRecordCertf(citz, Goal.CriminalRecordCertf); // Goal ???
                citz.setPDFDocument(pdf);
                pdf.openDoc(pdf.getPath());
                System.out.println("Se procede a mostrar el certificado de antecedentes penales.");
            } else {
                System.out.println("No se ha seleccionado ningún ministerio de justicia.");
            }
        } else {
            throw new NotValidPINException("El PIN introducido no es correcto y no se corresponde con el generado por el sistema previamente. Se indica al usuario que podria no estar vigente.");
        }
    }
    public  void enterForm (Citizen citz, Goal goal) throws IncompleteFormException, IncorrectVerificationException, ConnectException {

    }

    public void enterCred (Nif nif, Password passw) throws NifNotRegisteredException, NotValidCredException, AnyMobileRegisteredException, ConnectException {
        citz.setNif(nif);
        citz.setPassword(passw);
        byte cred = authMethod.ckeckCredent(nif, passw);
        switch (cred) {
            case 0 -> throw new NifNotRegisteredException("El ciudadano no está registrado en el sistema Cl@ve PIN.");

            case 1 -> System.out.println("Los datos del usuario son correctos pero no se ha escogido el método reforzado.");

            case 2 -> System.out.println("Los datos del usuario son correctos y se ha escogido el método reforzado.");
        }
    }

    private  void realizePayment () {

    }
    public  void enterCardData (CreditCard cardD) throws IncompleteFormException, NotValidPaymentDataException, InsufficientBalanceException, ConnectException {

    }
    public void obtainCertificate () throws BadPathException, DigitalSignatureException, ConnectException {

    }

    public void printDocument () throws BadPathException, PrintingException {
        printDocument(citz.getPDFDocument().getPath());
    }

    // The setter methods for injecting the dependences
    // Other input events (not required)
    // Other internal operations (not required)

    public void registerPayment () {

    }
    public void openDocument (DocPath path) throws BadPathException {
        try {
            citz.getPDFDocument().openDoc(path);
        } catch (IOException e) {
            throw new BadPathException("El documento no se ha podido abrir debido a que el path no es correcto.");
        }
    }
    public void printDocument (DocPath path)  throws BadPathException, PrintingException {
        if (!new File(path.getDocPath()).exists()) throw new BadPathException("El documento no se ha podido imprimir debido a que el path no es correcto.");
        System.out.println("El documento se ha enviado de forma correcta para su impresión.");
    }
}
