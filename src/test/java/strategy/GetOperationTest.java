package strategy;

import dto.QueryDto;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import service.handler.GetOperation;
import service.handler.OperationHandler;
import storage.QueryStorage;
import static org.junit.Assert.assertTrue;

public class GetOperationTest {
    private static OperationHandler handler;

    @BeforeClass
    public static void beforeClass() {
        handler = new GetOperation();
    }

    @Before
    public void init() {
        QueryStorage.storage.clear();
    }

    @Test
    public void apply_correctWorkOperation_ok() {
        QueryDto actual = new QueryDto("C", new String[]{"1", "1"},
                new String[]{"8", "15", "1"}, "P", new String[]{"15.10.2012"}, 83);
        QueryDto queryDtoTypeD = new QueryDto("D", new String[]{"1", "1"},
                new String[]{"8"}, "P", new String[]{"15.10.2012"}, 83);
        QueryStorage.storage.add(actual);
        handler.apply(queryDtoTypeD);
        assertTrue(handler.apply(queryDtoTypeD));
    }
}
