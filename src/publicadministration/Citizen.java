package publicadministration;

import data.Nif;

public class Citizen {

    private Nif nif;
    private String name;
    private String address;
    private String mobileNumb;

    public Citizen(String name, String add, String mobile) {
        this.name = name;
        this.address = add;
        this.mobileNumb = mobile;
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

