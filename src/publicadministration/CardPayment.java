package publicadministration;

import data.Nif;

import java.math.BigDecimal;
import java.util.Date;

public class CardPayment {

    private final String reference;
    private final Nif nif;
    private final Date date;
    private final BigDecimal importOfPayment;

    public CardPayment(String reference, Nif nif, Date date, BigDecimal imp) {
        this.reference = reference;
        this.nif = nif;
        this.date = date;
        this.importOfPayment = imp;
    }

    public String getReference() {
        return reference;
    }

    public Nif getNif() {
        return nif;
    }

    public Date getDate() {
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
