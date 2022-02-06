package service.handler;

import dto.QueryDto;
import storage.QueryStorage;

public class AddOperation implements OperationHandler {
    @Override
    public boolean apply(QueryDto queryDto) {
        QueryStorage.storage.add(queryDto);
        return true;
    }
}
