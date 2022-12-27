package data;

import exceptions.WrongSmallCodeFormatException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmallCodeTest {
    @Test
    public void getSmallCodeTest() throws WrongSmallCodeFormatException {
        SmallCode smallCode = new SmallCode("598");
        String correctSmallCode = "598";
        assertEquals(correctSmallCode, smallCode.getSmallCode());
    }

    @Test
    public void nullSmallCodeTest(){
        NullPointerException nullException = assertThrows(NullPointerException.class, () -> new SmallCode(null));
        assertEquals("Small Code cannot be null.", nullException.getMessage());
    }

    @Test
    public void shortSmallCodeTest() {
        WrongSmallCodeFormatException shortException = assertThrows(WrongSmallCodeFormatException.class, () -> new SmallCode("13"));
        assertEquals("Small Code must be 3 characters long.", shortException.getMessage());
    }

    @Test
    public void longSmallCodeTest() {
        WrongSmallCodeFormatException longException = assertThrows(WrongSmallCodeFormatException.class, () -> new SmallCode("3589"));
        assertEquals("Small Code must be 3 characters long.", longException.getMessage());
    }

    @Test
    public void incorrectSmallCodeTest() {
        WrongSmallCodeFormatException incorrectException = assertThrows(WrongSmallCodeFormatException.class, () -> new SmallCode("53f"));
        assertEquals("Incorrect SmallCode format. Cannot contain non-digit characters. Remember that it must be 3 characters long.", incorrectException.getMessage());
    }

    @Test
    public void correctSmallCode() {
        assertDoesNotThrow(() -> new SmallCode("598"));
    }

}
