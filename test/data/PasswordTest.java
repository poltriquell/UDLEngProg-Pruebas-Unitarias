package data;

import exceptions.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class PasswordTest {

    @Test
    public void nullPasswordTest() {
        assertThrows(NullPointerException.class, () -> new Password(null));
    }

    @Test
    public void shortPasswordTest() {
        assertThrows(WrongPasswordFormatException.class,
                () -> new Password("123"));
    }

    @Test
    public void noDigitPasswordTest() {
        assertThrows(WrongPasswordFormatException.class,
                () -> new Password("Password"));
    }

    @Test
    public void noCharPasswordTest() {
        assertThrows(WrongPasswordFormatException.class,
                () -> new Password(")(&%$#"));
    }

    @Test
    public void noCapitalLetterPasswordTest() {
        assertThrows(WrongPasswordFormatException.class,
                () -> new Password("password1234"));
    }

    @Test
    public void noSpecialCharPasswordTest() {
        assertThrows(WrongPasswordFormatException.class,
                () -> new Password("Password1234"));
    }

    @Test
    public void getPasswordTest() throws WrongPasswordFormatException {
        Password password = new Password("Password1234%");
        assertEquals("Password1234%", password.getPassword());
    }
}
