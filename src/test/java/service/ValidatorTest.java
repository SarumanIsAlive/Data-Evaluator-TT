package service;

import org.junit.BeforeClass;
import org.junit.Test;
import service.validator.Validator;
import service.validator.ValidatorImpl;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidatorTest {
    private static Validator validator;

    @BeforeClass
    public static void beforeClass() {
        validator = new ValidatorImpl();
    }

    @Test
    public void isValid_correctWork_ok() {
        String line = "C 1.1 8.15.1 P 15.10.2012 83";
        boolean actual = validator.isValid(line);
        assertTrue(actual);
    }

    @Test
    public void isValid_null_notOk() {
        String line = null;
        assertFalse(validator.isValid(line));
    }

    @Test
    public void isValid_withOutOperation_notOk() {
        String line = "Y 1.1 8.15.1 P 15.10.2012 83";
        boolean actual = validator.isValid(line);
        assertFalse(actual);
    }

    @Test
    public void isValid_withService_notOk() {
        String line = "C 8.15.1 P 15.10.2012 83";
        boolean actual = validator.isValid(line);
        assertFalse(actual);
    }

    @Test
    public void isValid_withOutQuestion_notOk() {
        String line = "C 1.1 P 15.10.2012 83";
        boolean actual = validator.isValid(line);
        assertFalse(actual);
    }

    @Test
    public void isValid_emptyLine_notOk() {
        String line = "";
        boolean actual = validator.isValid(line);
        assertFalse(actual);
    }

    @Test
    public void isValid_incorrectLine_notOk() {
        String line = "%";
        boolean actual = validator.isValid(line);
        assertFalse(actual);
    }
}
