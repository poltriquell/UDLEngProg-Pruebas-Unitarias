package publicadministration;

import data.Nif;
import publicadministration.exceptions.WrongMobileFormatException;

public class Citizen {

    private Nif nif;
    private String name;
    private String address;
    private String mobileNumb;

    public Citizen(String name, String add, String mobile) throws WrongMobileFormatException {
        checkValidCitizen(name, add, mobile);
        this.nif = new Nif("81871430D");
        this.name = name;
        this.address = add;
        this.mobileNumb = mobile;
    }

    private void checkValidCitizen(String name, String add, String mobile) throws WrongMobileFormatException {
        if(name == null) throw new NullPointerException("The name cannot be null");
        if(add == null) throw new NullPointerException("The address cannot be null");
        if(mobile == null) throw new NullPointerException("The mobile number cannot be null");

        if(mobile.length() != 9) throw new WrongMobileFormatException("The mobile number length is not correct. Remember the length must be 9");
        if(notValidMobile(mobile)) throw new WrongMobileFormatException("The mobile number format is not correct");
    }

    private boolean notValidMobile(String mobile) {
        char[] mobileNumb = mobile.toCharArray();
        int firstDigit = Integer.parseInt(String.valueOf(mobileNumb[0]));

        if(firstDigit != 6 && firstDigit != 7) {
            return true;
        }

        for (int i = 1; i < mobileNumb.length; i++) {
            if (!Character.isDigit(mobileNumb[i])) return true;
        }

        return false;
    }

    public Nif getNif() {
        return nif;
    }

    public String getAddress() {
        return address;
    }

    public String getMobileNumb() {
        return mobileNumb;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Citizen{ nif= " + nif + " name= " + name + " address= " + address + " mobileNumb= " + mobileNumb + "}";
    }
}

