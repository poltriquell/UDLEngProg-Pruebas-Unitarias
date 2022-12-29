package publicadministration;

import org.junit.jupiter.api.Test;
import publicadministration.exceptions.WrongCrimConvictionFormatException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class CrimConvictionTest {

    @Test
    public void getCrimConvictionTest() throws WrongCrimConvictionFormatException {
        CrimConviction crmC = new CrimConviction(LocalDate.of(2022, 12, 27), "Driving while being drunk", "250€ fine");
        LocalDate date = LocalDate.of(2022, 12, 27);
        String offense = "Driving while being drunk";
        String sentence = "250€ fine";

        assertEquals(date, crmC.getCommitDate());
        assertEquals(offense, crmC.getOffense());
        assertEquals(sentence, crmC.getSentence());
    }

    @Test
    public void nullCommitDateTest() {
        NullPointerException nullDate = assertThrows(NullPointerException.class, () -> new CrimConviction(null, "Driving without license", "1 year"));
        assertEquals("The crim conviction date cannot be null.", nullDate.getMessage());
    }

    @Test
    public void nullOffenseTest() {
        NullPointerException nullOffense = assertThrows(NullPointerException.class, () -> new CrimConviction(LocalDate.of(2022, 12, 27), null, "3000€ fine"));
        assertEquals("The crim conviction offense cannot be null.", nullOffense.getMessage());
    }

    @Test
    public void nullSentenceTest() {
        NullPointerException nullSentence = assertThrows(NullPointerException.class, () -> new CrimConviction(LocalDate.of(2022, 12, 27), "No offense taken.", null));
        assertEquals("The crim conviction sentence cannot be null.", nullSentence.getMessage());
    }

    @Test
    public void nonValidDateTest() {
        WrongCrimConvictionFormatException nonValidDate = assertThrows(WrongCrimConvictionFormatException.class, () -> new CrimConviction(LocalDate.of(2028, 12, 27), "Driving without license", "1 year"));
        assertEquals("You cannot register a future crim conviction.", nonValidDate.getMessage());
    }

    @Test
    public void correctConvictionTest() {
        assertDoesNotThrow(() -> new CrimConviction(LocalDate.of(2022, 12, 27), "Driving while being drunk", "250€ fine"));
    }
}
