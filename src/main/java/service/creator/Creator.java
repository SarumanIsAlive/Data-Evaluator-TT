package service.creator;

import dto.QueryDto;
import java.util.List;

public interface Creator {
    List<String> createReport(List<QueryDto> dtoList);
}
