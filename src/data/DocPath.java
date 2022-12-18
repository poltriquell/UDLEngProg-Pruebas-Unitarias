package data;

final public class DocPath {
    //Path to the directory where the documents are stored
    private final String path;

    public DocPath(String path) {
        checkCorrectPath(path);
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DocPath docPath = (DocPath) o;

        return path.equals(docPath.path);
    }

    @Override
    public int hashCode() {
        return path.hashCode();
    }

    public String toString() {
        return "DocPath{" + "path='" + path + '\'' + '}';
    }

    private void checkCorrectPath(String path) {
        if (path == null) throw new NullPointerException("Path cannot be null");
    }
}
