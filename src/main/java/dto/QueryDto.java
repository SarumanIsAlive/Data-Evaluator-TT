package dto;

import java.util.Arrays;

public class QueryDto {
    public String typeOfQuery;
    public String[] service;
    public String[] question;
    public String responseType;
    public String[] dates;
    public int time;

    public QueryDto(String typeOfQuery, String[] service, String[] question,
                    String responseType, String[] dates) {
        this.typeOfQuery = typeOfQuery;
        this.service = service;
        this.question = question;
        this.responseType = responseType;
        this.dates = dates;
    }

    public QueryDto(String typeOfQuery, String[] service, String[] question,
                    String responseType, String[] dates, int time) {
        this.typeOfQuery = typeOfQuery;
        this.service = service;
        this.question = question;
        this.responseType = responseType;
        this.dates = dates;
        this.time = time;
    }

    public String getTypeOfQuery() {
        return typeOfQuery;
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
    public String toString() {
        return "QueryDto{" + "typeOfQuery='" + typeOfQuery + '\''
                + ", service=" + Arrays.toString(service)
                + ", question=" + Arrays.toString(question)
                + ", responseType='" + responseType + '\''
                + ", dates=" + Arrays.toString(dates)
                + ", time=" + time + '}';
    }
}
