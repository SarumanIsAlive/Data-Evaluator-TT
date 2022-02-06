package service.handler;

import dto.QueryDto;

public interface OperationHandler {
    boolean apply(QueryDto queryDto);
}
