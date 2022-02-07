package service.parser;

import dto.QueryDto;
import service.validator.Validator;

public class ParserImpl implements Parser {
    private Validator validator;
    private static final String REGEX_DOT = "\\.";
    private static final String REGEX_SPACE = " ";
    private static final String REGEX_DASH = "-";
    private static final String TYPE_OF_LINE_C = "C";
    private static final String TYPE_OF_LINE_D = "D";

    public ParserImpl(Validator validator) {
        this.validator = validator;
    }

    @Override
    public QueryDto parseLine(String line) {
        if (validator.isValid(line)) {
            String[] query = line.split(REGEX_SPACE);
            if (query[0].equals(TYPE_OF_LINE_C)) {
                return new QueryDto(TYPE_OF_LINE_C, query[1].split(REGEX_DOT),
                        query[2].split(REGEX_DOT), query[3], query[4].split(REGEX_DASH),
                        Integer.parseInt(query[5]));
            }
            if (query[0].equals(TYPE_OF_LINE_D)) {
                return new QueryDto(TYPE_OF_LINE_D, query[1].split(REGEX_DOT),
                        query[2].split(REGEX_DOT), query[3], query[4].split(REGEX_DASH));
            }
        }
        throw new RuntimeException("Not correct line: " + line);
    }
}
