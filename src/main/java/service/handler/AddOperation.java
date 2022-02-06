package service.handler;

import dto.QueryDto;
import storage.Storage;

public class AddOperation implements OperationHandler {
    @Override
    public boolean apply(QueryDto queryDto) {
        Storage.storage.add(queryDto);
        return true;
    }
}
