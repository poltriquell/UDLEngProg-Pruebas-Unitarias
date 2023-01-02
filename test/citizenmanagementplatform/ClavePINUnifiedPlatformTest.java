package citizenmanagementplatform;

import citizenmanagementplatform.exceptions.IncompleteFormException;
import citizenmanagementplatform.exceptions.ProceduralException;
import data.Nif;
import data.SmallCode;
import dummiescertificationauthority.ClavePINCertificationAuthority;
import exceptions.WrongNifFormatException;
import exceptions.WrongPasswordFormatException;
import exceptions.WrongSmallCodeFormatException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import publicadministration.Citizen;
import publicadministration.exceptions.DigitalSignatureException;
import publicadministration.exceptions.WrongMobileFormatException;
import services.exceptions.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
        platform.registerCitizen(citz.getNif(), LocalDate.of(2025, 1, 1));

        citz.setValidationDate(LocalDate.now());
        platform.setAuthenticationMethod(new ClavePINCertificationAuthority(citz));
    }

    // enterNIFandPINobt method tests

    @Test
    public void notRegisteredNIFTest() throws NifNotRegisteredException, IncorrectValDateException, AnyMobileRegisteredException, ConnectException, NotValidCredException, WrongMobileFormatException, WrongNifFormatException {
        platform.booleanDebug();
        assertThrows(NifNotRegisteredException.class, () -> {platform.enterNIFandPINobt(new Nif("49255398R"), LocalDate.of(2025, 1, 1));});
    }

    @Test
    public void notCorrectDateTest() {
        platform.booleanDebug();
        assertThrows(NotValidCredException.class, () -> {platform.enterNIFandPINobt(citz.getNif(), LocalDate.of(2025, 3, 24));});
    }

    @Test
    public void notValidMobileNumbTest() {
        platform.booleanDebug();
        citz.setMobileNumb(null);
        assertThrows(NotValidCredException.class, () -> {platform.enterNIFandPINobt(citz.getNif(), LocalDate.of(2025, 1, 1));});
    }

    // enterPIN method tests

    @Test
    public void enterInvalidPINTest() {
        platform.booleanDebug();
        assertThrows(NotValidPINException.class, () -> {platform.enterPIN(new SmallCode("222"));});
    }
}
