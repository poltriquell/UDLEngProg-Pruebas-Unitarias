package publicadministration;

import data.Nif;
import publicadministration.exceptions.WrongImportValueException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;

public class CardPayment {

    int REFERENCE_LENGTH = 9;

    private final String reference;
    private final Nif nif;
    private final LocalDate date;
    private final BigDecimal importOfPayment;

    public CardPayment(Nif nif, BigDecimal imp) throws WrongImportValueException {
        checkValidImport(imp);
        this.reference = generateReference();
        this.nif = nif;
        this.date = LocalDate.now();
        this.importOfPayment = imp;
    }

    private void checkValidImport(BigDecimal imp) throws WrongImportValueException {
        if(imp == null) throw new NullPointerException("The value of the import cannot be null.");
        if(imp.compareTo(new BigDecimal("0")) < 0) throw new WrongImportValueException("You cannot introduce a negative import.");
    }

    private String generateReference() {
        StringBuilder ref = new StringBuilder();
        Random rndm = new Random();

        for(int i = 0; i < REFERENCE_LENGTH; i++) {
            ref.append(rndm.nextInt(0, 9));
        }

        return ref.toString();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CardPayment that = (CardPayment) o;

        if (getReference() != null ? !getReference().equals(that.getReference()) : that.getReference() != null)
            return false;
        if (getNif() != null ? !getNif().equals(that.getNif()) : that.getNif() != null) return false;
        if (getDate() != null ? !getDate().equals(that.getDate()) : that.getDate() != null) return false;
        return getImportOfPayment() != null ? getImportOfPayment().equals(that.getImportOfPayment()) : that.getImportOfPayment() == null;
    }

    @Override
    public int hashCode() {
        int result = getReference() != null ? getReference().hashCode() : 0;
        result = 31 * result + (getNif() != null ? getNif().hashCode() : 0);
        result = 31 * result + (getDate() != null ? getDate().hashCode() : 0);
        result = 31 * result + (getImportOfPayment() != null ? getImportOfPayment().hashCode() : 0);
        return result;
    }

    public String getReference() {
        return reference;
    }

    public Nif getNif() {
        return nif;
    }

    public LocalDate getDate() {
        return date;
    }

    public BigDecimal getImportOfPayment() {
        return importOfPayment;
    }

    @Override
    public String toString () {
        return "CardPayment{ reference= " + reference + " nif= " + nif + " date= " + date + "import= " + "}";
    }
}
