package publicadministration;

import data.Nif;
import exceptions.WrongNifFormatException;
import org.junit.jupiter.api.Test;
import publicadministration.exceptions.WrongImportValueException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.WeakHashMap;

import static org.junit.jupiter.api.Assertions.*;

public class CardPaymentTest {

    private final Nif defaultNif;

    public CardPaymentTest() throws WrongNifFormatException {
        this.defaultNif = new Nif("81871430D");
    }

    @Test
    public void getCardPaymentTest() throws WrongNifFormatException, WrongImportValueException {
        CardPayment cardP = new CardPayment(defaultNif, new BigDecimal("1.35"));
        Nif nif = new Nif("81871430D");
        LocalDate date = LocalDate.now();
        BigDecimal imprt = new BigDecimal("1.35");

        assertEquals(nif, cardP.getNif());
        assertEquals(date, cardP.getDate());
        assertEquals(imprt, cardP.getImportOfPayment());
    }

    @Test
    public void nullImportTest() {
        NullPointerException nullImport = assertThrows(NullPointerException.class, () -> new CardPayment(defaultNif, null));
        assertEquals("The value of the import cannot be null.", nullImport.getMessage());
    }

    @Test
    public void negativeImportTest() {
        WrongImportValueException negativeImport = assertThrows(WrongImportValueException.class, () -> new CardPayment(defaultNif, new BigDecimal("-1.35")));
        assertEquals("You cannot introduce a negative import.", negativeImport.getMessage());
    }

}
