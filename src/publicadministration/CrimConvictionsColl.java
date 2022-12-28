package publicadministration;

import publicadministration.exceptions.RepeatedCrimConvictionException;

import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;

public class CrimConvictionsColl {

    private final ArrayList<CrimConviction> crimConvictions;

    public CrimConvictionsColl() {
        this.crimConvictions = new ArrayList<>();
    }

    // Adds a criminal conviction
    public void addCriminalConviction(CrimConviction crmC) throws RepeatedCrimConvictionException {
        if(crmC == null) throw new NullPointerException("A new conviction cannot be null.");
        if(repeatedCrimConviction(crmC)) throw new RepeatedCrimConvictionException("This conviction is already registered.");

        crimConvictions.add(crmC);
    }

    private boolean repeatedCrimConviction(CrimConviction crmC) {
        for(CrimConviction crmConv : crimConvictions) {
            if(crmC.equals(crmConv)) {
                return true;
            }
        }
        return false;
    }

    // Gets a specific criminal conviction by date
    public CrimConviction getCriminalConviction(Date date){
        if(date == null) throw new NullPointerException("A date cannot be null.");
        if(date.after(Calendar.getInstance().getTime())) throw new IllegalArgumentException("You cannot introduce a future date.");

        for(CrimConviction crmC : crimConvictions) {
            if(crmC.getCommitDate().equals(date)) {
                return crmC;
            }
        }

        return null;
    }

    public String toString() {
        StringBuilder crimConvictionsStr = new StringBuilder("Crim convictions: ");
        for (CrimConviction crmC : crimConvictions) {
            crimConvictionsStr.append(crmC.toString());
        }

        return crimConvictionsStr.toString();
    }
}
