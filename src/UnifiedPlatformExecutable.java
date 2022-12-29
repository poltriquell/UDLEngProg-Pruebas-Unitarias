import citizenmanagementplatform.UnifiedPlatformInterface;
import citizenmanagementplatform.UnifiedPlatform;

public class UnifiedPlatformExecutable {
    public static void main(String[] args) throws Exception {
        UnifiedPlatform up = new UnifiedPlatform();
        System.out.println("\033[32mUnified Platform STARTED\033[0m");
        System.out.println("Seleccionar el ministerio deseado " +
                "\n1. Ministerio de Justicia \n2. Ministerio de Sanidad \n3. Ministerio de Educación \n4. Ministerio de Trabajo\n____________________________________________");

        java.util.Scanner keyboard = new java.util.Scanner(System.in);
        String option = keyboard.nextLine();
        System.out.println("Has seleccionado la opción " + option + " Ministerio de Justicia");
        System.out.println("Seleccionar apartado deseado " +
                "\n1. Trámites \n2. Información \n3. Contacto\n____________________________________________");
        option = keyboard.nextLine();
        up.selectProcedures();
        System.out.println("Seleccionar el trámite deseado " +
                "\n1. Obtener el certificado de antecedentes penales\n____________________________________________");
        option = keyboard.nextLine();
        up.selectCriminalReportCertf();
        System.out.println("Seleccionar el método de autenticación deseado " +
                "\n1. Cl@ve PIN \n2. Cl@ve Permanente\n____________________________________________");
        option = keyboard.nextLine();
        up.selectAuthMethod(Byte.parseByte(option));
        System.out.println("Introducir el NIF");
        String NIF = keyboard.nextLine();
        System.out.println("Introducir data de validación");
        String valdate = keyboard.nextLine();


    }
}
