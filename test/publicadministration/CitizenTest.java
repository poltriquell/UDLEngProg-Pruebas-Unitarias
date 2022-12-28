package publicadministration;

import org.junit.jupiter.api.Test;
import publicadministration.exceptions.WrongMobileFormatException;

import static org.junit.jupiter.api.Assertions.*;

public class CitizenTest {

    @Test
    public void getCitizenTest() throws WrongMobileFormatException {
        Citizen citizen = new Citizen("Alex Stoia", "C/ Cervantes, 17", "600000000");
        String name = "Alex Stoia";
        String address = "C/ Cervantes, 17";
        String mobile = "600000000";

        assertEquals(name, citizen.getName());
        assertEquals(address, citizen.getAddress());
        assertEquals(mobile, citizen.getMobileNumb());
    }

    @Test
    public void nullNameTest() {
       NullPointerException nullName = assertThrows(NullPointerException.class, () -> new Citizen(null, "C/ Cervantes, 17", "600000000"));
       assertEquals("The name cannot be null", nullName.getMessage());
    }

    @Test
    public void nullAddressTest() {
        NullPointerException nullAddress = assertThrows(NullPointerException.class, () -> new Citizen("Pol Triquell", null, "622558899"));
        assertEquals("The address cannot be null", nullAddress.getMessage());
    }

    @Test
    public void nullMobileTest() {
        NullPointerException nullMobile = assertThrows(NullPointerException.class, () -> new Citizen("Enric Tobeña", "C/ Cervantes, 17", null));
        assertEquals("The mobile number cannot be null", nullMobile.getMessage());
    }

    @Test
    public void shortMobileTest() {
        WrongMobileFormatException shortMobile = assertThrows(WrongMobileFormatException.class, () -> new Citizen("Alex Stoia", "C/ Cervantes, 17", "69654123"));
        assertEquals("The mobile number length is not correct. Remember the length must be 9", shortMobile.getMessage());
    }

    @Test
    public void longMobileTest() {
        WrongMobileFormatException longMobile = assertThrows(WrongMobileFormatException.class, () -> new Citizen("Pol Triquell", "C/ Cervantes, 17", "6965412363"));
        assertEquals("The mobile number length is not correct. Remember the length must be 9", longMobile.getMessage());
    }

    @Test
    public void wrongFirstDigitTest() {
        WrongMobileFormatException wrongFirstDig = assertThrows(WrongMobileFormatException.class, () -> new Citizen("Enric Tobeña", "C/ Cervantes, 17", "874563212"));
        assertEquals("The mobile number format is not correct", wrongFirstDig.getMessage());
    }

    @Test
    public void nonDigitTest() {
        WrongMobileFormatException nonDigit = assertThrows(WrongMobileFormatException.class, () -> new Citizen("Enric Tobeña", "C/ Cervantes, 17", "674563J12"));
        assertEquals("The mobile number format is not correct", nonDigit.getMessage());
    }
}
