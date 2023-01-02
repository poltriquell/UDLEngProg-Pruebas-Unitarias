import citizenmanagementplatform.UnifiedPlatform;
import citizenmanagementplatform.exceptions.ProceduralException;
import data.*;
import exceptions.*;
import publicadministration.Citizen;
import publicadministration.CriminalRecordCertf;
import publicadministration.exceptions.*;
import services.*;
import services.exceptions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class UnifiedPlatformExecutable {

    static UnifiedPlatform up;
    static Scanner keyboard = new Scanner(System.in);
    static Citizen citizen = new Citizen();
    static Goal goal = null;

    public static void main(String[] args) throws Exception {
        up = new UnifiedPlatform();
        System.out.println("\033[32mSe ha iniciado la ejecución de la Unified Platform\033[0m");

        selectMinistry();
        selectProcedure();
        selectCertificate();

        selectAuthMethod();
        introduceNIFandValDate();
        introducePIN();
        enterForm();

        okVerification();
        paymentImport();
        realizePayment();
        registerPayment();

        certificateOptions();
        printDocument();
    }

    private static void selectMinistry() {
        System.out.println("""
                Seleccionar el ministerio deseado\s
                1. Ministerio de Justicia\s
                2. Ministerio de Sanidad\s
                3. Ministerio de Educación\s
                4. Ministerio de Trabajo
                ____________________________________________""");

        keyboard.nextLine();
        up.selectJusMin();
    }

    private static void selectProcedure() {
        System.out.println("""
                Seleccionar apartado deseado\s
                1. Trámites\s
                2. Información\s
                3. Contacto
                ____________________________________________""");

        keyboard.nextLine();
        up.selectProcedures();
    }

    private static void selectCertificate() {
        System.out.println("""
                Seleccionar el trámite deseado\s
                1. Obtener el certificado de antecedentes penales
                ___________________________________________""");

        keyboard.nextLine();
        up.selectCriminalReportCertf();
    }

    private static void selectAuthMethod() {
        System.out.println("""
                Seleccionar el método de autenticación deseado\s
                1. Cl@ve PIN\s
                2. Cl@ve Permanente
                ____________________________________________""");

        String method = keyboard.nextLine();
        up.selectAuthMethod(Byte.parseByte(method));
    }

    private static void introduceNIFandValDate() throws WrongNifFormatException, NotValidCredException, IncorrectValDateException, NifNotRegisteredException, AnyMobileRegisteredException, ConnectException,  WrongSmallCodeFormatException, ProceduralException {
        System.out.println("Introducir el Nombre:");
        String name = keyboard.nextLine(); //Take the nif code from the user
        citizen.setName(name);

        System.out.println("Introducir el NIF:");
        String nifCode = keyboard.nextLine(); //Take the nif code from the user
        Nif newNif = new Nif(nifCode); //Create a new Nif object with the nif code
        citizen.setNif(newNif); //Set the citizen's nif

        System.out.println("Introducir fecha de validación en formato dd/mm/aaaa:");
        String date = keyboard.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate valDate = LocalDate.parse(date, formatter);
        citizen.setValidationDate(valDate);

        System.out.println("Introducir número de teléfono móvil:");
        String mobile = keyboard.nextLine();
        citizen.setMobileNumb(mobile);

        up.registerCitizen(newNif, valDate);

        up.enterNIFandPINobt(citizen.getNif(), citizen.getValidationDate());

        System.out.println("PIN generado: " + up.associatePIN());

    }

    private static void introducePIN() throws WrongSmallCodeFormatException, NotValidPINException, ConnectException, ProceduralException {
        String PINcode = keyboard.nextLine();
        up.enterPIN(new SmallCode(PINcode));
    }

    private static void enterForm() throws ConnectException, WrongGoalFormatException, IncorrectVerificationException, ProceduralException {
        System.out.println("Introduce el detalle del objetivo del certificado:");
        String goalStr = keyboard.nextLine();

        System.out.println("Introduce el tipo de objetivo del certificado (WORKWITHMINORS, GAMESECTOR, PUBLICWORKERS, PUBLICADMINCONSORTIUM):");
        String goalType = keyboard.nextLine();
        goalTypes newGoalType = goalTypes.valueOf(goalType);
        Goal goal = new Goal(goalStr, newGoalType);
        UnifiedPlatformExecutable.goal = goal;
        up.enterForm(citizen, goal);
    }

    private static void okVerification() {
        System.out.println("\033[36mVerificación correcta\033[0m");
    }

    private static void paymentImport() {
        System.out.println("El importe a pagar es de 15 euros");
    }

    private static void realizePayment() throws NotValidPaymentDataException{
        System.out.println("Introduce los datos de la tarjeta de crédito:");
        System.out.println("Número de tarjeta:");
        String cardNumb = keyboard.nextLine();
        System.out.println("Fecha de caducidad en formato dd/mm/yyyy:");
        String expDate = keyboard.nextLine();
        System.out.println("CVV:");
        String cvv = keyboard.nextLine();

        enterCardData(cardNumb, expDate, cvv);
    }

    private static void enterCardData(String cardNumb, String expDate, String cvv) throws NotValidPaymentDataException{
        CASImpl cas = new CASImpl();
        cas.askForApproval("000000000", cardNumb, expDate, new BigDecimal(15));
    }

    private static void registerPayment() {
        //ACTUALIZAR EL ESTADO DE LA TRANSACCIÓN
    }

    private static void certificateOptions() throws WrongCrimConvictionFormatException, RepeatedCrimConvictionException {
        System.out.println("¿Desea el certificado apostillado? \n1. Sí \n2. No");
        String opcion = keyboard.nextLine();

        if (opcion.equals("1")) {
            JusticeMinistryImpl generateCertf = new JusticeMinistryImpl();
            CriminalRecordCertf certificate = generateCertf.getCriminalRecordCertf(citizen, goal);
            System.out.println("\033[32mEl certificado SIN apostilla en formato PDF ha sido generado correctamente\033[0m");
            System.out.println(certificate);

        } else {
            System.out.println("Opción de apostillado no implementada");
        }
    }

    private static void printDocument() throws InterruptedException {
        System.out.println("¿Desea imprimir el documento? \n1. Sí \n2. No");
        String opcion = keyboard.nextLine();

        if (opcion.equals("1")) {
            System.out.println("\033[32mIniciando el proceso de impresión\033[0m");
            Thread.sleep(1500);
            System.out.println("\033[36mImpresion completada\033[0m");
        } else {
            System.out.println("Opción de apostillado no implementada");
        }
    }

}
