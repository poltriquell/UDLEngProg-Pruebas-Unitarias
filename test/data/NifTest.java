package data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NifTest {

    @Test
    public void getNifTest() {
        Nif nif = new Nif(new String("81871430D"));
        String correctNif = "81871430D";
        assertEquals(correctNif, nif.getNif());
    }

    @Test
    public void nullNifTest(){
        assertThrows(NullPointerException.class,
                () -> new Nif(null));
    }

    @Test
    public void shortNif() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    new Nif(new String("123"));
                });
    }

    @Test
    public void longNif() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    new Nif(new String("81871430D1"));
                });
    }

    @Test
    public void lowerCaseNif() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    new Nif(new String("81871430d"));
                });
    }

    @Test
    public void wrongNif() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    new Nif(new String("81871430E"));
                });
    }

}
