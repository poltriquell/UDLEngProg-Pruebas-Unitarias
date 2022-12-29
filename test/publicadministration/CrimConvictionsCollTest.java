package publicadministration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import publicadministration.exceptions.RepeatedCrimConvictionException;
import publicadministration.exceptions.WrongCrimConvictionFormatException;
import publicadministration.exceptions.WrongDateFormatException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class CrimConvictionsCollTest {

    CrimConvictionsColl convictions;

    @BeforeEach
    public void buildCrimConvicitonsColl() throws WrongCrimConvictionFormatException, RepeatedCrimConvictionException {
        this.convictions = new CrimConvictionsColl();
        convictions.addCriminalConviction(new CrimConviction(LocalDate.of(2020, 12, 27), "Driving while being drunk", "250€ fine"));
        convictions.addCriminalConviction(new CrimConviction(LocalDate.of(2021, 10, 12), "Driving without license", "1 year"));
        convictions.addCriminalConviction(new CrimConviction(LocalDate.of(2022, 8, 4), "Stealing someone's private information", "2 years"));
    }

    @Test
    public void getNullDateTest() {
       NullPointerException nullDate = assertThrows(NullPointerException.class,() -> convictions.getCriminalConviction(null));
       assertEquals("A date cannot be null.", nullDate.getMessage());
    }

    @Test
    public void getFutureDateTest() {
        WrongDateFormatException futureDate = assertThrows(WrongDateFormatException.class,() -> convictions.getCriminalConviction(LocalDate.of(2025, 11, 30)));
        assertEquals("You cannot get a future date.", futureDate.getMessage());
    }

    @Test
    public void dateWithNoConvictionsTest() throws WrongDateFormatException {
        assertNull(convictions.getCriminalConviction(LocalDate.of(2019, 7, 18)));
    }

    @Test
    public void getCrimConviction() throws WrongCrimConvictionFormatException, WrongDateFormatException {
        CrimConviction getCrimConv = new CrimConviction(LocalDate.of(2020, 12, 27), "Driving while being drunk", "250€ fine");
        assertEquals(getCrimConv, convictions.getCriminalConviction(LocalDate.of(2020, 12, 27)));
    }

    @Test
    public void addNullCrimConvTest(){
        NullPointerException nullCrimConv = assertThrows(NullPointerException.class,() -> convictions.addCriminalConviction(null));
        assertEquals("A new conviction cannot be null.", nullCrimConv.getMessage());
    }

    @Test
    public void addRepeatedCrimConvTest() throws WrongCrimConvictionFormatException {
        CrimConviction repeatedConv = new CrimConviction(LocalDate.of(2021, 10, 12), "Driving without license", "1 year");
        RepeatedCrimConvictionException repeatedCC = assertThrows(RepeatedCrimConvictionException.class,() -> this.convictions.addCriminalConviction(repeatedConv));
        assertEquals("This conviction is already registered.", repeatedCC.getMessage());
    }

    @Test
    public void addCorrectCrimConvTest() throws WrongCrimConvictionFormatException {
        CrimConviction correctConv = new CrimConviction(LocalDate.of(2022, 10, 12), "New offense", "New sentence");
        assertDoesNotThrow(() -> this.convictions.addCriminalConviction(correctConv));
    }

}
