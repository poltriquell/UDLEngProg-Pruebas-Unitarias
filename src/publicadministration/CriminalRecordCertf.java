package publicadministration;

import data.DigitalSignature;
import data.Goal;
import data.Nif;

public class CriminalRecordCertf extends PDFDocument {

    private Nif nif;
    private String name;
    private Goal goal;
    private DigitalSignature digSign;
    private CrimConvictionsColl crimConvs;

    public CriminalRecordCertf(Nif nif, String name, Goal g, DigitalSignature ds, CrimConvictionsColl crmC) {
        checkCriminalRecordCertf(nif, name, g, ds, crmC);
        this.nif = nif;
        this.name = name;
        this.goal = g;
        this.digSign = ds;
        this.crimConvs = crmC;
    }

    private void checkCriminalRecordCertf(Nif nif, String name, Goal g, DigitalSignature ds, CrimConvictionsColl crmC) {
        if(nif == null) throw new NullPointerException("The nif cannot be null");
        if(name == null) throw new NullPointerException("The name cannot be null");
        if(g == null) throw new NullPointerException("The goal cannot be null");
        if(ds == null) throw new NullPointerException("The digital signature cannot be null");
        if(crmC == null) throw new NullPointerException("The crim convictions collection cannot be null");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CriminalRecordCertf that = (CriminalRecordCertf) o;

        if (getNif() != null ? !getNif().equals(that.getNif()) : that.getNif() != null) return false;
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        if (getGoal() != null ? !getGoal().equals(that.getGoal()) : that.getGoal() != null) return false;
        if (getDigSign() != null ? !getDigSign().equals(that.getDigSign()) : that.getDigSign() != null) return false;
        return getCrimConvs() != null ? getCrimConvs().equals(that.getCrimConvs()) : that.getCrimConvs() == null;
    }

    @Override
    public int hashCode() {
        int result = getNif() != null ? getNif().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getGoal() != null ? getGoal().hashCode() : 0);
        result = 31 * result + (getDigSign() != null ? getDigSign().hashCode() : 0);
        result = 31 * result + (getCrimConvs() != null ? getCrimConvs().hashCode() : 0);
        return result;
    }

    public Nif getNif() {
        return nif;
    }

    public String getName() {
        return name;
    }

    public Goal getGoal() {
        return goal;
    }

    public DigitalSignature getDigSign() {
        return digSign;
    }

    public CrimConvictionsColl getCrimConvs() {
        return crimConvs;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.append("nif" + nif).append("name" + name).append("goal" + goal).append("digSign" + digSign).append("crimConvs" + crimConvs).toString();
    }

}
