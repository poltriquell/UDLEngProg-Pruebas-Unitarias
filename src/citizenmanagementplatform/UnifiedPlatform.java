package citizenmanagementplatform;

import citizenmanagementplatform.exceptions.BadPathException;
import citizenmanagementplatform.exceptions.ProceduralException;
import exceptions.WrongSmallCodeFormatException;
import publicadministration.Citizen;
import data.*;
import publicadministration.CreditCard;
import services.CertificationAuthority;
import services.CertificationAuthorityInterface;
import services.GPDImpl;
import services.exceptions.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class UnifiedPlatform implements UnifiedPlatformInterface {
    Citizen citz = new Citizen();
    CertificationAuthorityInterface authMethod;

    public static ArrayList<String> possibleAuthenticationMethods;

    boolean procedureInCourse = false;
    boolean isClavePINAuthenticationMethod = false;
    boolean clavePINInCourse = false;
    boolean isVerifiedClavePIN = false;
    boolean hasGPDVerifiedCitizenData = false;
    boolean isVerifiedPayment = false;

    public void booleanDebug() {
        procedureInCourse = true;
        isClavePINAuthenticationMethod = true;
        clavePINInCourse = true;
        isVerifiedClavePIN = true;
        hasGPDVerifiedCitizenData = true;
        isVerifiedPayment = true;
    }

    public UnifiedPlatform() {

        possibleAuthenticationMethods = new ArrayList<>(); //Create the list of possible authentication methods
        setAuthenticationMethods(); // Set the possible authentication methods into the ArrayList

    }

    private void setAuthenticationMethods() {
        //We could use more authentication methods, but for the sake of simplicity we will use only one.
        possibleAuthenticationMethods.add("Cl@ve PIN");
        possibleAuthenticationMethods.add("Cl@ve Permanente");
    }

    // Input events

    public void selectJusMin () {
        System.out.println("Se ha hecho click en la sección Ministerio de Justicia en el mosaico inicial.");
    }

    public void selectProcedures() {
        System.out.println("Se ha hecho click en el enlace \"Trámites\" de la sección de la SS.");
        procedureInCourse = true;
    }

    public void selectCriminalReportCertf () {
        System.out.println("Se ha seleccionado el trámite \"Obtener el certificado de antecedentes penales\".");
    }

    public void selectAuthMethod (byte opc) {
        // Assuming that Authentication methods are stored in the same order as they are displayed to the user in the GUI
        String selectedAuthenticationMethod = possibleAuthenticationMethods.get(opc - 1);
        System.out.println("Se ha seleccionado el siguiente método de autenticación : " + selectedAuthenticationMethod);
        if (selectedAuthenticationMethod.equals("Cl@ve PIN")) {isClavePINAuthenticationMethod = true;}
    }

    public void registerCitizen(Nif nif, LocalDate valDate) {
        authMethod = new CertificationAuthority(citz);
        citz.setNif(nif);
        citz.setValidationDate(valDate);
        citz.setMobileNumb(new String("666666666")); // We set a dummy mobile number
    }

    public void enterNIFandPINobt (Nif nif, LocalDate valDate) throws NifNotRegisteredException, IncorrectValDateException, AnyMobileRegisteredException, ConnectException, NotValidCredException, ProceduralException {
        if (!procedureInCourse || !isClavePINAuthenticationMethod) {throw new ProceduralException("No se puede seguir con la obtención del certificado de antecedentes penales debido a que no se han completado ciertos pasos anteriores.");}

        //Como se indica en el contrato, sólo se usara el método de autenticación Cl@ve PIN
        if (authMethod.sendPIN(nif, valDate)) {
            nif = citz.getNif();
            System.out.println("Se envia el PIN al usuario con DNI -> " + nif);
        } else {
            throw new ConnectException("Ha ocurrido un error al enviar el PIN al número de teléfono móvil correspondiente.");
        }
        clavePINInCourse = true;
    }

    public void enterPIN (SmallCode pin) throws NotValidPINException, ConnectException, ProceduralException {
        if (!procedureInCourse || !isClavePINAuthenticationMethod || !clavePINInCourse) {throw new ProceduralException("No se puede seguir con la obtención del certificado de antecedentes penales debido a que no se han completado ciertos pasos anteriores.");}

        //Create JUSTICE MINISTRY CLASS
        if (authMethod.checkPIN(citz.getNif(), pin)) {
            System.out.println("El PIN introducido es correcto y se corresponde con el generado por el sistema previamente. Recuerde que el PIN es válido durante 24 horas desde su generación.");
        } else {
            throw new NotValidPINException("El PIN introducido no es correcto y no se corresponde con el generado por el sistema previamente. Es posible que el PIN haya caducado.");
        }
        isVerifiedClavePIN = true;
    }

    public  void enterForm (Citizen citz, Goal goal) throws IncorrectVerificationException, ConnectException, ProceduralException {
        if (!procedureInCourse || !isClavePINAuthenticationMethod || !clavePINInCourse || !isVerifiedClavePIN) {throw new ProceduralException("No se puede seguir con la obtención del certificado de antecedentes penales debido a que no se han completado ciertos pasos anteriores.");}

        GPDImpl gpd = new GPDImpl();
        gpd.verifyData(citz, goal);

        hasGPDVerifiedCitizenData = true;
    }

    public void enterCred (Nif nif, Password passw) throws NifNotRegisteredException, NotValidCredException, AnyMobileRegisteredException, ConnectException {
        byte cred = authMethod.checkCredent(nif, passw);
        switch (cred) {
            case 0 -> throw new NifNotRegisteredException("El ciudadano no está registrado en el sistema Cl@ve PIN.");

            case 1 -> System.out.println("Los datos del usuario son correctos pero no se ha escogido el método reforzado.");

            case 2 -> System.out.println("Los datos del usuario son correctos y se ha escogido el método reforzado.");
        }
    }

    public String associatePIN() throws WrongSmallCodeFormatException {
        String pin = SmallCode.generateSmallCode();
        citz.setPIN(new SmallCode(pin));
        return pin;
    }

    public  void realizePayment () {
        isVerifiedPayment = true;
    }

    public  void enterCardData (CreditCard cardD) throws ProceduralException {
        if (!procedureInCourse || !isClavePINAuthenticationMethod || !clavePINInCourse || !isVerifiedClavePIN || !hasGPDVerifiedCitizenData) {throw new ProceduralException("No se puede seguir con la obtención del certificado de antecedentes penales debido a que no se han completado ciertos pasos anteriores.");}
    }

    public void obtainCertificate () throws ProceduralException {
        if (!procedureInCourse || !isClavePINAuthenticationMethod || !clavePINInCourse || !isVerifiedClavePIN || !hasGPDVerifiedCitizenData || !isVerifiedPayment) {throw new ProceduralException("No se puede seguir con la obtención del certificado de antecedentes penales debido a que no se han completado ciertos pasos anteriores.");}
    }

    public void printDocument () throws BadPathException {
        printDocument(citz.getPDFDocument().getPath());
    }

    // The setter methods for injecting the dependences
    // Other input events (not required)
    // Other internal operations (not required)

    public void registerPayment () {System.out.println("Se ha registrado el pago del certificado de antecedentes penales.");}
    public void openDocument (DocPath path) throws BadPathException {
        try {
            citz.getPDFDocument().openDoc(path);
        } catch (IOException e) {
            throw new BadPathException("El documento no se ha podido abrir debido a que el path no es correcto.");
        }
    }
    public void printDocument (DocPath path)  throws BadPathException {
        if (!new File(path.getPath()).exists()) throw new BadPathException("El documento no se ha podido imprimir debido a que el path no es correcto.");
        System.out.println("El documento se ha enviado de forma correcta para su impresión.");
    }

    // Some other useful methods (not required).

    public void setAuthenticationMethod(CertificationAuthorityInterface method) {
        this.authMethod = method;
    }

}