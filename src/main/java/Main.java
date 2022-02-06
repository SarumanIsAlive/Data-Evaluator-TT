import dto.QueryDto;
import service.creator.Creator;
import service.creator.CreatorImpl;
import service.parser.Parser;
import service.parser.ParserImpl;
import service.reader.MyReader;
import service.reader.MyReaderImpl;
import service.validator.ValidatorImpl;
import service.writer.MyWriter;
import service.writer.MyWriterImpl;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    private static final String INPUT_FILE_DESTINATION = "src/main/resources/input.txt";
    private static final String OUTPUT_FILE_DESTINATION = "src/main/resources/output.txt";
    private static final Parser parser = new ParserImpl(new ValidatorImpl());
    private static final Creator creator = new CreatorImpl();
    private static final MyReader myReader = new MyReaderImpl();
    private static final MyWriter myWriter = new MyWriterImpl();

    public static void main(String[] args) {
        List<String> lineList = myReader.readFromFile(INPUT_FILE_DESTINATION);
        List<QueryDto> dtoList = lineList.stream()
                .skip(1)
                .map(parser::parseLine)
                .collect(Collectors.toList());
        List<String> report = creator.createReport(dtoList);
        myWriter.writeToFile(report, OUTPUT_FILE_DESTINATION);
    }
}
