package citizenmanagementplatform;

import citizenmanagementplatform.exceptions.IncompleteFormException;
import data.Nif;
import dummiescertificationauthority.ClavePINCertificationAuthority;
import exceptions.WrongNifFormatException;
import exceptions.WrongPasswordFormatException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import publicadministration.Citizen;
import publicadministration.exceptions.WrongMobileFormatException;
import services.exceptions.AnyMobileRegisteredException;
import services.exceptions.ConnectException;
import services.exceptions.IncorrectVerificationException;
import services.exceptions.NifNotRegisteredException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class ClavePINUnifiedPlatformTest {

    Citizen citz;

    UnifiedPlatform platform;

    @BeforeEach
    public void setUp() throws WrongMobileFormatException, WrongNifFormatException, IncompleteFormException, IncorrectVerificationException, ConnectException {

        citz = new Citizen("Jake Peralta", "Calle Hispanidad 12", "612101210");
        citz.setNif(new Nif("99571829E"));

        platform = new UnifiedPlatform();

        //citz.setValidationDate(LocalDate.of(1987, 1, 1));
        //citz.setValidationDate(LocalDate.now());

        Calendar cal = Calendar.getInstance();
        cal.set(1987, Calendar.MARCH, 24);

        citz.setValidationDate(LocalDate.now());
        platform.setAuthenticationMethod(new ClavePINCertificationAuthority(citz));
    }

}
