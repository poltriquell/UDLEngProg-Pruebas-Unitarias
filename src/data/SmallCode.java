package data;

final public class SmallCode {

    private final String smallCode;

    public SmallCode(String smallCode) {
        checkValidSmallCode(smallCode);
        this.smallCode = smallCode;
    }

    private void checkValidSmallCode(String smallCode) {
        if (smallCode == null) throw new NullPointerException("Small Code cannot be null.");
        if (smallCode.length() != 3) throw new IllegalArgumentException("Small Code must be 3 characters long.");
        if (wrongSmallCodeFormat(smallCode)) throw new IllegalArgumentException("Incorrect SmallCode format. Cannot contain non-digit characters. Remember that it must be 3 characters long.");
    }

    private boolean wrongSmallCodeFormat(String smallCode) {
        char[] smallCodeArray = smallCode.toCharArray();
        for(char c : smallCodeArray) {
            if(!Character.isDigit(c)) return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SmallCode smallCode1 = (SmallCode) o;

        return smallCode.equals(smallCode1.smallCode);
    }

    @Override
    public int hashCode() {
        return smallCode.hashCode();
    }

    public String getSmallCode() {
        return smallCode;
    }

    @Override
    public String toString() {
        return "Small Code{}"; //Falta completar el mètode
    }


}
