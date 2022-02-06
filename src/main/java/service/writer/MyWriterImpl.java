package service.writer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

public class MyWriterImpl implements MyWriter {
    public void writeToFile(List<String> report, String fileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            for (String line : report) {
                bufferedWriter.write(line);
            }
        } catch (Exception exception) {
            throw new RuntimeException("Can't write data to the file " + fileName, exception);
        }
    }
}
