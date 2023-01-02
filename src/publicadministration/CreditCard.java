package publicadministration;

import data.Nif;
import data.SmallCode;
import publicadministration.exceptions.WrongCreditCardFormatException;

import java.time.LocalDate;

public class CreditCard {

    private final Nif nif;
    private final String cardNumb;
    private final LocalDate expirDate;
    private final SmallCode svc;

    public CreditCard (Nif nif, String cNum, LocalDate d, SmallCode c) throws WrongCreditCardFormatException {
        checkCreditCard(cNum, d);
        this.nif = nif;
        this.cardNumb = cNum;
        this.expirDate = d;
        this.svc = c;
    }

    private void checkCreditCard(String cNum, LocalDate d) throws WrongCreditCardFormatException {
        if(cNum == null) throw new NullPointerException("The card number cannot be null.");
        if(d == null) throw new NullPointerException("The expire date cannot be null.");

        if(cNum.length() != 16) throw new WrongCreditCardFormatException("The length of the credit card number is not correct. It must be 16");
        if(invalidCardNum(cNum)) throw new WrongCreditCardFormatException("The credit card number cannot contain non-digit characters.");
        if(timedOutDate(d)) throw new WrongCreditCardFormatException("The validity of this credit card has finished");
    }

    private boolean invalidCardNum(String cardNumb) {
        char[] cNum = cardNumb.toCharArray();

        for(char ch : cNum) {
            if(!Character.isDigit(ch)) return true;
        }

        return false;
    }

    private boolean timedOutDate(LocalDate date) {
        return date.isBefore(LocalDate.now());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CreditCard that = (CreditCard) o;

        if (getNif() != null ? !getNif().equals(that.getNif()) : that.getNif() != null) return false;
        if (getCardNumb() != null ? !getCardNumb().equals(that.getCardNumb()) : that.getCardNumb() != null)
            return false;
        if (getExpirDate() != null ? !getExpirDate().equals(that.getExpirDate()) : that.getExpirDate() != null)
            return false;
        return getSvc() != null ? getSvc().equals(that.getSvc()) : that.getSvc() == null;
    }

    @Override
    public int hashCode() {
        int result = getNif() != null ? getNif().hashCode() : 0;
        result = 31 * result + (getCardNumb() != null ? getCardNumb().hashCode() : 0);
        result = 31 * result + (getExpirDate() != null ? getExpirDate().hashCode() : 0);
        result = 31 * result + (getSvc() != null ? getSvc().hashCode() : 0);
        return result;
    }

    public Nif getNif() {
        return nif;
    }

    public String getCardNumb() {
        return cardNumb;
    }

    public LocalDate getExpirDate() {
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
