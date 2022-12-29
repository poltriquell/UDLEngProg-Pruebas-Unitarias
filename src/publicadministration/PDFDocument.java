package publicadministration;

import data.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class PDFDocument {

    private Date creatDate;
    private DocPath path;
    private File file;

    private final String defaultDocPath = "C:/Program Files/PDFDirectory/document.pdf";

    public PDFDocument() {
        this.creatDate = new Date();
        this.path = new DocPath(defaultDocPath);
        this.file = new File(this.path.getPath());
    }

    public String toString () {
        return "PDF Document{ creatDate= " + creatDate.toString() + " path= " + path.toString() + " }";
    }

    public void moveDoc(DocPath destPath) throws IOException {
        File newFile = new File(destPath.getPath());

        if (!newFile.exists()) throw new IOException("The introduced path does not exist");
        Path initialPath = Paths.get(path.getPath());
        Path finalPath = Paths.get(defaultDocPath);

        Files.move(initialPath, finalPath, REPLACE_EXISTING);
        System.out.println("The document has been moved from " + path + " to " + destPath);
        path = destPath;
    }

    public void openDoc(DocPath path) throws IOException{
        File newFile = new File(path.getPath());
        if (!newFile.exists()) throw new IOException("The specified document does not exist.");

        File file = new File(path.getPath());
        Desktop.getDesktop().open(file);
    }

    public Date getCreatDate() {
        return creatDate;
    }

    public DocPath getPath() {
        return path;
    }

    public File getFile() {
        return file;
    }

    public String getDefaultDocPath() {
        return defaultDocPath;
    }
}
