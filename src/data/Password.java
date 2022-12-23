package data;

import exceptions.WrongPasswordFormatException;

final public class Password {

    private final String password;

    public Password(String passwd) throws WrongPasswordFormatException {
        checkPassword(passwd);
        this.password = passwd;
    }

    private void checkPassword(String passwd) throws WrongPasswordFormatException {
        if (passwd == null) {
            throw new NullPointerException("Password cannot be null.");
        } else if (passwd.length() < 4) {
            throw new WrongPasswordFormatException("Password must have at least 4 characters.");
        } else if (!passwordHasDigit(passwd)) {
            throw new WrongPasswordFormatException("Password must have at least one digit.");
        } else if (!passwordHasChar(passwd)) {
            throw new WrongPasswordFormatException("Password must have at least one character.");
        } else if (!passwordHasCapitalLetter(passwd)) {
            throw new WrongPasswordFormatException("Password must have at least one capital letter.");
        } else if (!passwordHasSpecialChar(passwd)) {
            throw new WrongPasswordFormatException("Password must have at least one special character.");
        }
    }

    private boolean passwordHasDigit(String pass) {
        char[] passwd = pass.toCharArray();
        for (char c : passwd) {
            if (Character.isDigit(c))
                return true;
        }
        return false;
    }

    private boolean passwordHasChar(String pass) {
        char[] passwd = pass.toCharArray();
        for (char c : passwd) {
            if (Character.isAlphabetic(c))
                return true;
        }
        return false;
    }

    private boolean passwordHasSpecialChar(String pass) {
        char[] passwd = pass.toCharArray();
        for (char c : passwd) {
            if (!Character.isAlphabetic(c) && !Character.isDigit(c) && !Character.isSpaceChar(c))
                return true;
        }
        return false;
    }

    private boolean passwordHasCapitalLetter(String pass) {
        char[] passwd = pass.toCharArray();
        for (char c : passwd) {
            if (Character.isUpperCase(c))
                return true;
        }
        return false;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Password passwd = (Password) o;
        return password.equals(passwd.password);
    }

    @Override
    public int hashCode() {
        return password.hashCode();
    }

    @Override
    public String toString() {
        return "Password{" + "Cityzen Password='" + password + '\'' + '}';
    }

}
