package data;

import exceptions.WrongNifFormatException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NifTest {

    @Test
    public void getNifTest() throws WrongNifFormatException {
        Nif nif = new Nif("81871430D");
        String correctNif = "81871430D";
        assertEquals(correctNif, nif.getNif());
    }

    @Test
    public void nullNifTest(){
        NullPointerException nullNif = assertThrows(NullPointerException.class, () -> new Nif(null));
        assertEquals("NIF cannot be null", nullNif.getMessage());
    }

    @Test
    public void shortNifTest() {
        WrongNifFormatException shortNif = assertThrows(WrongNifFormatException.class, () -> new Nif("123"));
        assertEquals("NIF must be 9 characters long", shortNif.getMessage());
    }

    @Test
    public void longNifTest() {
        WrongNifFormatException longNif = assertThrows(WrongNifFormatException.class, () -> new Nif("81871430D1"));
        assertEquals("NIF must be 9 characters long", longNif.getMessage());
    }

    @Test
    public void lowerCaseNifTest() {
        WrongNifFormatException lowerCaseNif = assertThrows(WrongNifFormatException.class, () -> new Nif("81871430d"));
        assertEquals("Incorrect NIF format, check it again, remember 8 numbers and 1 capital letter", lowerCaseNif.getMessage());
    }

    @Test
    public void wrongNifTest() {
        WrongNifFormatException wrongNif = assertThrows(WrongNifFormatException.class, () -> new Nif("81871430E"));
        assertEquals("Incorrect NIF format, check it again, remember 8 numbers and 1 capital letter", wrongNif.getMessage());
    }

    @Test
    public void correctNifTest() {
        assertDoesNotThrow(() -> new Nif("48059124A"));
    }
}
