package service;

import org.junit.BeforeClass;
import org.junit.Test;
import service.writer.MyWriter;
import service.writer.MyWriterImpl;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class MyWriterTest {
    private static MyWriter fileWriter;

    @BeforeClass
    public static void beforeClass() {
        fileWriter = new MyWriterImpl();
    }

    @Test
    public void writeToFile_correctWork_ok() throws IOException {
        List<String> report = List.of("report test#1");
        String filePath = "src/test/resourcesTest/outputTest.txt";
        fileWriter.writeToFile(report,filePath);
        List<String> actual = List.of("report test#1");
        List<String> expected = Files.readAllLines(Paths.get(filePath));
        assertEquals(expected, actual);
    }

    @Test(expected = RuntimeException.class)
    public void writeToFile_nullAndNull_notOk() {
        List<String> report = null;
        String filePath = null;
        fileWriter.writeToFile(report,filePath);
    }

    @Test(expected = RuntimeException.class)
    public void writeToFile_reportAndNull_notOk() {
        List<String> report = List.of("report test#3");
        String filePath = null;
        fileWriter.writeToFile(report,filePath);
    }

    @Test(expected = RuntimeException.class)
    public void writeToFile_nullAndFileName_notOk() {
        List<String> report = null;
        String fileName = "src/test/resourcesTest/outputTest.txt";
        fileWriter.writeToFile(report,fileName);
    }

    @Test(expected = RuntimeException.class)
    public void writeToFile_incorrectFileName_notOk() {
        List<String> report = List.of("report test#5");
        String fileName = "crs/tset/errscyrtes/fileOutputter.dvd";
        fileWriter.writeToFile(report, fileName);
    }
}
