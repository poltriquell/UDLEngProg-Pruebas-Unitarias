package data;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DigitalSignatureTest {

    @Test
    public void getDigitalSignatureTest() {
        byte[] digitalSignatureByte = new byte[]{1, 2, 3, 4, 5};
        DigitalSignature digitalSignature = new DigitalSignature(digitalSignatureByte);
        assertEquals(digitalSignatureByte, digitalSignature.getSignature());
    }

    @Test
    public void nullDigitalSignatureTest(){
        assertThrows(NullPointerException.class,
                () -> new DigitalSignature(null));
    }

}
