import citizenmanagementplatform.UnifiedPlatformInterface;
import citizenmanagementplatform.UnifiedPlatform;
import data.*;
import exceptions.WrongNifFormatException;
import exceptions.WrongSmallCodeFormatException;
import publicadministration.Citizen;
import publicadministration.exceptions.DigitalSignatureException;
import publicadministration.exceptions.WrongMobileFormatException;
import services.exceptions.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
}
