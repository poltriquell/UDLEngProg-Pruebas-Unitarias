package publicadministration;

import data.Nif;
import data.SmallCode;

import java.util.Date;

public class CreditCard {

    private final Nif nif;
    private final String cardNumb;
    private final Date expirDate;
    private final SmallCode svc;

    public CreditCard (Nif nif, String cNum, Date d, SmallCode c) {
        this.nif = nif;
        this.cardNumb = cNum;
        this.expirDate = d;
        this.svc = c;
    }

    public Nif getNif() {
        return nif;
    }

    public String getCardNumb() {
        return cardNumb;
    }

    public Date getExpirDate() {
        return expirDate;
    }

    public SmallCode getSvc() {
        return svc;
    }

    @Override
    public String toString () {
        return "CreditCard{ nif= " + nif + " cardNum= " + cardNumb + " expirDate= " + expirDate + "svc= " + svc + "}";
    }

}
