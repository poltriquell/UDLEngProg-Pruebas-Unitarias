package data;

final public class Nif {

    // The tax identification number in the Spanish state.
    private final String nif;

    public Nif (String code) {
        checkValidNIF(code);
        this.nif = code;
    }

    public String getNif () { return nif; }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nif niff = (Nif) o;
        return nif.equals(niff.nif);
    }

    @Override
    public int hashCode () { return nif.hashCode(); }

    @Override
    public String toString () {
        return "Nif{" + "nif ciudadano='" + nif + '\'' + '}';
    }

    private void checkValidNIF (String code) {
        if (code == null) throw new NullPointerException("NIF cannot be null");
        if (code.length() != 9) throw new IllegalArgumentException("NIF must be 9 characters long");
        if (!correctNIF(code)) throw new IllegalArgumentException("Incorrect NIF format, check it again, remember 8 numbers and 1 capital letter");
    }

    private boolean correctNIF(String code) { //Check if the NIF is in the correct format, not checking NIE
        char[] nif = code.toCharArray();
        int sum = 0;

        for (int i = 0; i < 8; i++) {
            if (!Character.isDigit(nif[i])) return false;
            sum += Character.getNumericValue(nif[i]);
        }

        int residue = sum % 23; //Used to check a letter
        char[] validLetters = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};
        return Character.isUpperCase(nif[8]) && nif[8] == validLetters[residue];
    }
}