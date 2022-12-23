package data;

import exceptions.WrongPasswordFormatException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordTest {
    Password password;

    @BeforeEach
    public void setUp() throws WrongPasswordFormatException {
        password = new Password("PassWord1234%");
    }

    @Test
    public void getPasswordTest() {
        assertEquals("PassWord1234%", password.getPassword());
    }
}
