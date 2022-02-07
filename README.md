## data-evaluator

### Project purpose
Parsed validate data to Dto\
Depending on the operation (implementation of the strategy)\
Write to the Output.txt everything that is in ReportStorage\
Added Unit tests

###Project Structure
service(handler, reader, parser, writer, validator)\
model(OperationType)\
dto(QueryDto)\
storage(QueryStorage, ReportStorage)
