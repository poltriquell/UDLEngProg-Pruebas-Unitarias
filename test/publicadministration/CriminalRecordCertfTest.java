package publicadministration;

import data.DigitalSignature;
import data.Goal;
import data.Nif;
import data.goalTypes;
import exceptions.WrongGoalFormatException;
import exceptions.WrongNifFormatException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CriminalRecordCertfTest {

    private final Nif nif;
    private final String name;
    private final Goal goal;
    private final DigitalSignature digSign;
    private final CrimConvictionsColl crimConvs;

    public CriminalRecordCertfTest() throws WrongNifFormatException, WrongGoalFormatException {
        nif = new Nif("81871430D");
        name = "NameTest";
        goal = new Goal("Major", goalTypes.PUBLICWORKERS);
        digSign = new DigitalSignature(new byte[]{1, 2, 3, 4, 5});
        crimConvs = new CrimConvictionsColl();
    }

    @Test
    public void getCrimRecordCertfTest() throws WrongGoalFormatException, WrongNifFormatException {
        CriminalRecordCertf crCertf = new CriminalRecordCertf(nif, name, goal, digSign, crimConvs);
        Nif nif = new Nif("81871430D");
        String name = "NameTest";
        Goal goal = new Goal("Major", goalTypes.PUBLICWORKERS);

        assertEquals(nif, crCertf.getNif());
        assertEquals(name, crCertf.getName());
        assertEquals(goal, crCertf.getGoal());
        assertEquals(digSign, crCertf.getDigSign());
        assertEquals(crimConvs, crCertf.getCrimConvs());
    }

    @Test
    public void nullNifTest() {
        NullPointerException nullNif = assertThrows(NullPointerException.class, () -> new CriminalRecordCertf(null, name, goal, digSign, crimConvs));
        assertEquals("The nif cannot be null", nullNif.getMessage());
    }

    @Test
    public void nullNameTest() {
        NullPointerException nullName = assertThrows(NullPointerException.class, () -> new CriminalRecordCertf(nif, null, goal, digSign, crimConvs));
        assertEquals("The name cannot be null", nullName.getMessage());
    }

    @Test
    public void nullGoalTest() {
        NullPointerException nullGoal = assertThrows(NullPointerException.class, () -> new CriminalRecordCertf(nif, name, null, digSign, crimConvs));
        assertEquals("The goal cannot be null", nullGoal.getMessage());
    }

    @Test
    public void nullDigitalSignatureTest() {
        NullPointerException nullGoal = assertThrows(NullPointerException.class, () -> new CriminalRecordCertf(nif, name, goal, null, crimConvs));
        assertEquals("The digital signature cannot be null", nullGoal.getMessage());
    }

    @Test
    public void nullCrimCollectionTest() {
        NullPointerException nullCrimColl = assertThrows(NullPointerException.class, () -> new CriminalRecordCertf(nif, name, goal, digSign, null));
        assertEquals("The crim convictions collection cannot be null", nullCrimColl.getMessage());
    }
}
