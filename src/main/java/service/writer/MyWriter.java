package service.writer;

import java.util.List;

public interface MyWriter {
    void writeToFile(List<String> report, String fileName);
}
