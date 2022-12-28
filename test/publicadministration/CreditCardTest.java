package publicadministration;

import data.Nif;
import data.SmallCode;
import org.junit.jupiter.api.Test;
import publicadministration.exceptions.WrongCreditCardFormatException;
import services.exceptions.NotValidPaymentDataException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class CreditCardTest {

    private final Nif defaultNif = new Nif("81871430D");
    private final SmallCode defaultSvc = new SmallCode("343");

    @Test
    public void getCreditCardTest() throws NotValidPaymentDataException, WrongCreditCardFormatException {
        CreditCard crCd = new CreditCard(defaultNif, "1111222233334444", LocalDate.of(2023, 2, 25), defaultSvc);
        Nif nif = new Nif("81871430D");
        String cNum = "1111222233334444";
        LocalDate date = LocalDate.of(2023, 2, 25);
        SmallCode smCode =  new SmallCode("343");

        assertEquals(nif, crCd.getNif());
        assertEquals(cNum, crCd.getCardNumb());
        assertEquals(date, crCd.getExpirDate());
        assertEquals(smCode, crCd.getSvc());
    }

    @Test
    public void nullCreditCardNumberTest() {
        NullPointerException nullCNum = assertThrows(NullPointerException.class, () -> new CreditCard(defaultNif, null,  LocalDate.of(2023, 2, 25), defaultSvc));
        assertEquals("The card number cannot be null.", nullCNum.getMessage());
    }

    @Test
    public void nullExpireDateTest() {
        NullPointerException nullDate = assertThrows(NullPointerException.class, () -> new CreditCard(defaultNif, "1111222233334444", null, defaultSvc));
        assertEquals("The expire date cannot be null.", nullDate.getMessage());
    }

    @Test
    public void shortCreditCardNumberTest() {
        WrongCreditCardFormatException shortCNum = assertThrows(WrongCreditCardFormatException.class, () -> new CreditCard(defaultNif, "11112222333344",  LocalDate.of(2023, 2, 25), defaultSvc));
        assertEquals("The length of the credit card number is not correct. It must be 16", shortCNum.getMessage());
    }

    @Test
    public void longCreditCardNumberTest() {
        WrongCreditCardFormatException longCNum = assertThrows(WrongCreditCardFormatException.class, () -> new CreditCard(defaultNif, "11112222333344445", LocalDate.of(2023, 2, 25), defaultSvc));
        assertEquals("The length of the credit card number is not correct. It must be 16", longCNum.getMessage());
    }

    @Test
    public void nonDigitInCNumTest() {
        WrongCreditCardFormatException nonDigit = assertThrows(WrongCreditCardFormatException.class, () -> new CreditCard(defaultNif, "11112222333344M4",  LocalDate.of(2023, 2, 25), defaultSvc));
        assertEquals("The credit card number cannot contain non-digit characters.", nonDigit.getMessage());
    }

    @Test
    public void timedOutCardTest() {
        WrongCreditCardFormatException nonDigit = assertThrows(WrongCreditCardFormatException.class, () -> new CreditCard(defaultNif, "1111222233334444", LocalDate.of(2018, 10, 12), defaultSvc));
        assertEquals("The validity of this credit card has finished", nonDigit.getMessage());
    }

    @Test
    public void correctCardTest() {
        assertDoesNotThrow(() -> new CreditCard(defaultNif, "1111222233334444", LocalDate.of(2025, 10, 12), defaultSvc));
    }
}
