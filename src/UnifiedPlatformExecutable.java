import citizenmanagementplatform.UnifiedPlatform;
import citizenmanagementplatform.exceptions.IncompleteFormException;
import data.*;
import exceptions.WrongGoalFormatException;
import exceptions.WrongNifFormatException;
import exceptions.WrongSmallCodeFormatException;
import publicadministration.Citizen;
import publicadministration.exceptions.DigitalSignatureException;
import publicadministration.exceptions.WrongMobileFormatException;
import services.CASImpl;
import services.exceptions.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class UnifiedPlatformExecutable {

    static UnifiedPlatform up;
    static Scanner keyboard = new Scanner(System.in);
    static Citizen citizen = new Citizen();

    public static void main(String[] args) throws Exception {
        up = new UnifiedPlatform();
        System.out.println("\033[32mUnified Platform STARTED\033[0m");

        selectMinistry();
        selectProcedure();
        selectCertificate();

        selectAuthMethod();
        introduceNIFandValDate();
        introducePIN();
        enterForm();

        okVerificacion();
        importeAPagar();
        realizePayment();
        registerPayment();

        opcionesCertificado();
    }

    private static String selectMinistry() {
        System.out.println("Seleccionar el ministerio deseado " +
                "\n1. Ministerio de Justicia \n2. Ministerio de Sanidad \n3. Ministerio de Educación \n4. Ministerio de Trabajo\n____________________________________________");

        String chosenMinistry = keyboard.nextLine();
        up.selectJusMin();

        return chosenMinistry;
    }

    private static String selectProcedure() {
        System.out.println("Seleccionar apartado deseado " +
                "\n1. Trámites \n2. Información \n3. Contacto\n____________________________________________");

        String chosenProcedure = keyboard.nextLine();
        up.selectProcedures();
        return chosenProcedure;
    }

    private static String selectCertificate() {
        System.out.println("Seleccionar el trámite deseado " +
                "\n1. Obtener el certificado de antecedentes penales\n___________________________________________");

        String chosenCertf = keyboard.nextLine();
        up.selectCriminalReportCertf();
        return chosenCertf;
    }

    private static void selectAuthMethod() {
        System.out.println("Seleccionar el método de autenticación deseado " +
                "\n1. Cl@ve PIN \n2. Cl@ve Permanente\n____________________________________________");

        String method = keyboard.nextLine();
        up.selectAuthMethod(Byte.parseByte(method));
    }

    private static void introduceNIFandValDate() throws WrongNifFormatException, NotValidCredException, IncorrectValDateException, NifNotRegisteredException, AnyMobileRegisteredException, ConnectException, WrongMobileFormatException, WrongSmallCodeFormatException {
        System.out.println("Introducir el Nombre:");
        String name = keyboard.nextLine(); //Take the nif code from the user
        citizen.setName(name);

        System.out.println("Introducir el NIF:");
        String nifCode = keyboard.nextLine(); //Take the nif code from the user
        Nif newNif = new Nif(nifCode); //Create a new Nif object with the nif code
        citizen.setNif(newNif); //Set the citizen's nif

        System.out.println("Introducir fecha de validación en formato dd/mm/aaaa");
        String date = keyboard.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate valDate = LocalDate.parse(date, formatter);
        citizen.setValidationDate(valDate);

        System.out.println("Introducir número de teléfono móvil:");
        String mobile = keyboard.nextLine();
        citizen.setMobileNumb(new String(mobile));


        up.enterNIFandPINobt(citizen.getNif(), citizen.getValidationDate());
        String PIN = SmallCode.generateSmallCode();
        citizen.setPIN(new SmallCode(PIN));
        System.out.println("PIN generado: " + PIN);

    }

    private static void introducePIN() throws WrongSmallCodeFormatException, NotValidPINException, DigitalSignatureException, IOException, ConnectException {
        String PINcode = keyboard.nextLine();
        up.enterPIN(new SmallCode(PINcode));
    }

    private static void enterForm() throws ConnectException, WrongGoalFormatException, IncompleteFormException, IncorrectVerificationException {
        System.out.println("Introduce el detalle del objetivo del certificado:");
        String goal = keyboard.nextLine();
        System.out.println("Introduce el tipo de objetivo del certificado:");
        String goalType = keyboard.nextLine();
        goalTypes newGoalType = goalTypes.valueOf(goalType);
        up.enterForm(citizen, new Goal(goal, newGoalType));
    }

    private static void okVerificacion(){
        System.out.println("\033[36mVerificación correcta\033[0m");
    }

    private static void importeAPagar(){
        System.out.println("El importe a pagar es de 15 euros");
    }

    private static void realizePayment() throws NotValidPaymentDataException, InsufficientBalanceException, ConnectException {
        System.out.println("Introduce los datos de la tarjeta de crédito:");
        System.out.println("Número de tarjeta:");
        String cardNumb = keyboard.nextLine();
        System.out.println("Fecha de caducidad en formato dd/mm/yyyy:");
        String expDate = keyboard.nextLine();
        System.out.println("CVV:");
        String cvv = keyboard.nextLine();

        enterCardData(cardNumb, expDate, cvv);

    }

    private static void enterCardData(String cardNumb, String expDate, String cvv) throws NotValidPaymentDataException, InsufficientBalanceException, ConnectException {
        CASImpl cas = new CASImpl();
        cas.askForApproval(new String(String.valueOf(000000000)), cardNumb, new String(expDate) ,new BigDecimal(15));
    }

    private static void registerPayment(){
        //ACTUALIZAR EL ESTADO DE LA TRANSACCIÓN
    }

    private static void opcionesCertificado(){
        System.out.println("¿Desea obtener el certificado? \n1. Sí \n2. No");
        String opcion = keyboard.nextLine();
        if(opcion.equals("1")){
            System.out.println("El certificado se ha generado correctamente");
        }else{
            System.out.println("El certificado no se ha generado");
        }
    }

}
