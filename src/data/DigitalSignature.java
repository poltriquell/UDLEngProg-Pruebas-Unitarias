package data;

final public class DigitalSignature {
    private final byte[] signature;

    public DigitalSignature(byte[] signature) {
        checkSignature(signature);
        this.signature = signature;
    }

    private void checkSignature(byte[] signature) {
        if (signature == null) throw new NullPointerException("Signature cannot be null");
    }

    public byte[] getSignature() {
        return signature;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DigitalSignature that = (DigitalSignature) o;

        return signature.equals(that.signature);
    }

    @Override
    public int hashCode() {
        return signature.hashCode();
    }

    @Override
    public String toString() {
        return "DigitalSignature{" + "signature=" + signature + '}';
    }

}
