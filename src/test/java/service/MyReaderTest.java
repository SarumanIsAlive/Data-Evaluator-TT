package service;

import org.junit.BeforeClass;
import org.junit.Test;
import service.reader.MyReaderImpl;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class MyReaderTest {
    private static MyReaderImpl myReader;

    @BeforeClass
    public static void beforeClass() {
        myReader = new MyReaderImpl();
    }

    @Test
    public void readFromFile_correctFile_ok() {
        String fileName = "src/test/resourcesTest/inputTest.txt";
        List<String> expected = List.of("C 1.1 8.15.1 P 15.10.2012 83",
        "C 1 10.1 P 01.12.2012 65",
        "C 1.1 5.5.1 P 01.11.2012 117",
        "D 1.1 8 P 01.01.2012-01.12.2012");
        List<String> actual = myReader.readFromFile(fileName);
        assertEquals(expected, actual);
    }

    @Test(expected = NullPointerException.class)
    public void readFromFile_fileNameIsNull_notOk() {
        myReader.readFromFile(null);
    }

    @Test(expected = RuntimeException.class)
    public void readFromFile_incorrectFileName_notOk() {
        String filePath = "FilePath";
        myReader.readFromFile(filePath);
    }
}
