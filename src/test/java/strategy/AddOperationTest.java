package strategy;

import dto.QueryDto;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import service.handler.AddOperation;
import service.handler.OperationHandler;
import storage.QueryStorage;
import static org.junit.Assert.assertEquals;

public class AddOperationTest {
    private static OperationHandler handler;

    @BeforeClass
    public static void beforeClass() {
        handler = new AddOperation();
    }

    @Before
    public void init() {
        QueryStorage.storage.clear();
    }

    @Test
    public void apply_correctWorkOperation_ok() {
        QueryDto actual = new QueryDto("C", new String[]{"1", "1"},
                new String[]{"8", "15", "1"},
                "P", new String[]{"15.10.2012"}, 83);
        QueryStorage.storage.add(actual);
        QueryDto queryDto = new QueryDto("C", new String[]{"1", "1"},
                new String[]{"8", "15", "1"},
                "P", new String[]{"15.10.2012"}, 83);
        handler.apply(actual);
        assertEquals(actual, queryDto);
    }

    @Test
    public void apply_correctAddToStorageOperation_ok() {
        QueryDto actual = new QueryDto("C", new String[]{"1", "1"},
                new String[]{"8", "15", "1"},
                "P", new String[]{"15.10.2012"}, 83);
        handler.apply(actual);
        assertEquals(1, QueryStorage.storage.size());
    }
}
