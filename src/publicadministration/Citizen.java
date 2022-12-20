package publicadministration;

import data.Nif;

public class Citizen {
    // Represents all the information required for a citizen
    private Nif nif;
    private String name;
    private String address;
    private String mobileNumb;// Other additional information (not required)

    public Citizen (String name, String add, String mobile){ // Constructor
        this.name = name;
        this.address = add;
        this.mobileNumb = mobile;
    }
    public String toString () {
        return "Citizen{" +
                "nif=" + nif + ", name='" + name + '\'' + ", address='" + address + '\'' + ", mobileNumb='" + mobileNumb + '\'' + '}';
    }
}

