package service;

import dto.QueryDto;
import org.junit.BeforeClass;
import org.junit.Test;
import service.parser.Parser;
import service.parser.ParserImpl;
import service.validator.Validator;
import service.validator.ValidatorImpl;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ParserTest {
    private static Validator validator;
    private static Parser parser;

    @BeforeClass
    public static void beforeClass() {
        validator = new ValidatorImpl();
        parser = new ParserImpl(validator);
    }

    @Test
    public void parseLine_correctWork_ok() {
        String line = "C 1.1 8.15.1 P 15.10.2012 83";
        QueryDto actual = new QueryDto("C", new String[]{"1", "1"},
                new String[]{"8", "15", "1"},
                "P", new String[]{"15.10.2012"}, 83);
        QueryDto expected = parser.parseLine(line);
        assertEquals(actual, expected);
    }

    @Test(expected = RuntimeException.class)
    public void parseLine_null_notOk() {
        String line = null;
        parser.parseLine(line);
    }

    @Test(expected = RuntimeException.class)
    public void parseLine_withOutOperation_notOk() {
        String line = "1.1 8.15.1 P 15.10.2012 83";
        parser.parseLine(line);
    }

    @Test(expected = RuntimeException.class)
    public void parseLine_withOutService_notOk() {
        String line = "C 8.15.1 P 15.10.2012 83";
        parser.parseLine(line);
    }

    @Test(expected = RuntimeException.class)
    public void parseLine_emptyLine_notOk() {
        String line = "";
        parser.parseLine(line);
    }

    @Test(expected = RuntimeException.class)
    public void parseLine_incorrectLine_notOk() {
        String line = "B 13.1 8.15.1.4 H 15.10.2222 Cat";
        parser.parseLine(line);
    }
}
