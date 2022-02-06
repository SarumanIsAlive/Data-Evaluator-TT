package service.parser;

import dto.QueryDto;

public interface Parser {
    QueryDto parseLine(String line);
}
