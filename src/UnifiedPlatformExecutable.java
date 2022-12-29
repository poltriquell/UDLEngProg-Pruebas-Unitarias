import citizenmanagementplatform.UnifiedPlatformInterface;
import citizenmanagementplatform.UnifiedPlatform;
import data.Nif;
import exceptions.WrongNifFormatException;
import publicadministration.exceptions.WrongMobileFormatException;
import services.exceptions.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class UnifiedPlatformExecutable {

    static UnifiedPlatform up;
    static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        up = new UnifiedPlatform();
        System.out.println("\033[32mUnified Platform STARTED\033[0m");

        selectMinistry();
        selectProcedure();
        selectCertificate();

        selectAuthMethod();
        introduceNIFandValDate();

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
                "\n1. Obtener el certificado de antecedentes penales\n____________________________________________");

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

    private static void introduceNIFandValDate() throws WrongNifFormatException, NotValidCredException, IncorrectValDateException, NifNotRegisteredException, AnyMobileRegisteredException, ConnectException, WrongMobileFormatException {
        System.out.println("Introducir el NIF:");
        String nifCode = keyboard.nextLine();
        Nif newNif = new Nif(nifCode);

        System.out.println("Introducir fecha de validación en formato dd/mm/aaaa");
        String date = keyboard.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate valDate = LocalDate.parse(date, formatter);

        up.enterNIFandPINobt(newNif, valDate);
    }
}
