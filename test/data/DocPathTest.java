package data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DocPathTest {

    @Test
    public void getDocPathTest() {
        DocPath docPath = new DocPath(new String("C:\\Users\\Usuario\\Documents\\GitHub\\Practica\\src\\data"));
        String correctDocPath = "C:\\Users\\Usuario\\Documents\\GitHub\\Practica\\src\\data";
        assertEquals(correctDocPath, docPath.getPath());
    }

    @Test
    public void nullDocPathTest(){
        assertThrows(NullPointerException.class,
                () -> new DocPath(null));
    }

}
