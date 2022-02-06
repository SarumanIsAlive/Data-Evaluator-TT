
import model.OperationType;
import service.handler.AddOperation;
import service.handler.GetOperation;
import service.handler.OperationHandler;
import service.parser.Parser;
import service.parser.ParserImpl;
import service.reader.MyReader;
import service.reader.MyReaderImpl;
import service.validator.ValidatorImpl;
import service.writer.MyWriter;
import service.writer.MyWriterImpl;
import storage.Storage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    private static final String INPUT_FILE_DESTINATION = "src/main/resources/input.txt";
    private static final String OUTPUT_FILE_DESTINATION = "src/main/resources/output.txt";
    private static final Parser parser = new ParserImpl(new ValidatorImpl());
    private static final MyReader myReader = new MyReaderImpl();
    private static final MyWriter myWriter = new MyWriterImpl();

    public static void main(String[] args) {
        Map<String, OperationHandler> handlers = new HashMap<>();
        handlers.put(OperationType.ADD.getType(), new AddOperation());
        handlers.put(OperationType.GET.getType(), new GetOperation());

        List<String> lineList = myReader.readFromFile(INPUT_FILE_DESTINATION);
        lineList.stream()
                .skip(1)
                .map(parser::parseLine)
                .forEach(queryDto -> handlers.get(queryDto.getOperationType()).apply(queryDto));
        myWriter.writeToFile(Storage.report, OUTPUT_FILE_DESTINATION);
    }
}
