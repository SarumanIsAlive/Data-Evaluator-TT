package dto;

import java.util.Arrays;
import java.util.Objects;

public class QueryDto {
    public String operationType;
    public String[] service;
    public String[] question;
    public String responseType;
    public String[] dates;
    public int time;

    public QueryDto(String operationType, String[] service,
                    String[] question, String responseType, String[] dates) {
        this.operationType = operationType;
        this.service = service;
        this.question = question;
        this.responseType = responseType;
        this.dates = dates;
    }

    public QueryDto(String operationType, String[] service,
                    String[] question, String responseType, String[] dates, int time) {
        this.operationType = operationType;
        this.service = service;
        this.question = question;
        this.responseType = responseType;
        this.dates = dates;
        this.time = time;
    }

    public String getOperationType() {
        return operationType;
    }

    public String[] getService() {
        return service;
    }

    public String[] getQuestion() {
        return question;
    }

    public String getResponseType() {
        return responseType;
    }

    public String[] getDates() {
        return dates;
    }

    public int getTime() {
        return time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QueryDto queryDto = (QueryDto) o;
        return time == queryDto.time && Objects.equals(operationType, queryDto.operationType)
                && Arrays.equals(service, queryDto.service) && Arrays.equals(question, queryDto.question)
                && Objects.equals(responseType, queryDto.responseType) && Arrays.equals(dates, queryDto.dates);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(operationType, responseType, time);
        result = 31 * result + Arrays.hashCode(service);
        result = 31 * result + Arrays.hashCode(question);
        result = 31 * result + Arrays.hashCode(dates);
        return result;
    }

    @Override
    public String toString() {
        return "QueryDto{" + "operationType=" + operationType
                + ", service=" + Arrays.toString(service)
                + ", question=" + Arrays.toString(question)
                + ", responseType='" + responseType + '\''
                + ", dates=" + Arrays.toString(dates)
                + ", time=" + time + '}';
    }
}
