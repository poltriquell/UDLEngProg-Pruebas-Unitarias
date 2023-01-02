package publicadministration;

import publicadministration.exceptions.WrongCrimConvictionFormatException;

import java.time.LocalDate;

public class CrimConviction {

    private final LocalDate commitDate;
    private final String offense;
    private final String sentence;

    public CrimConviction(LocalDate commit, String off, String sentc) throws WrongCrimConvictionFormatException {
        checkCrimConviction(commit, off, sentc);
        this.commitDate = commit;
        this.offense = off;
        this.sentence = sentc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CrimConviction that = (CrimConviction) o;

        if (getCommitDate() != null ? !getCommitDate().equals(that.getCommitDate()) : that.getCommitDate() != null)
            return false;
        if (getOffense() != null ? !getOffense().equals(that.getOffense()) : that.getOffense() != null) return false;
        return getSentence() != null ? getSentence().equals(that.getSentence()) : that.getSentence() == null;
    }

    @Override
    public int hashCode() {
        int result = getCommitDate() != null ? getCommitDate().hashCode() : 0;
        result = 31 * result + (getOffense() != null ? getOffense().hashCode() : 0);
        result = 31 * result + (getSentence() != null ? getSentence().hashCode() : 0);
        return result;
    }

    private void checkCrimConviction(LocalDate commit, String off, String sentc) throws WrongCrimConvictionFormatException {
        if(commit == null) throw new NullPointerException("The crim conviction date cannot be null.");
        if(off == null) throw new NullPointerException("The crim conviction offense cannot be null.");
        if(sentc == null) throw new NullPointerException("The crim conviction sentence cannot be null.");

        if(notValidDate(commit)) throw new WrongCrimConvictionFormatException("You cannot register a future crim conviction.");
    }

    private boolean notValidDate(LocalDate commit) {
        return commit.isAfter(LocalDate.now());
    }

    public LocalDate getCommitDate() {
        return commitDate;
    }

    public String getOffense() {
        return offense;
    }

    public String getSentence() {
        return sentence;
    }

    public String toString () {
        return "CrimConviction{ date= " + commitDate + " offense= " + offense + " sentence= " + sentence + "}";    }
}
