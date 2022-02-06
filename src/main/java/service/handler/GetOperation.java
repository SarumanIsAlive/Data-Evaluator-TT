package service.handler;

import dto.QueryDto;
import storage.QueryStorage;
import storage.ReportStorage;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class GetOperation implements OperationHandler {
    private static final String DATE_FORMAT = "dd.MM.yyyy";
    private static final String ALL_MATCHES = "*";
    private static final List<QueryDto> STORAGE= QueryStorage.storage;
    private static final List<String> REPORT = ReportStorage.report;

    @Override
    public boolean apply(QueryDto typeOfQueryD) {
        int counter = 0;
        int sum = 0;
        for (int i = 0; i < QueryStorage.storage.size(); i++) {
            if (equalTypeOfService(typeOfQueryD.getService(), STORAGE.get(i).getService())
                    && equalTypeOfQuestion(typeOfQueryD.getQuestion(), STORAGE.get(i).getQuestion())
                    && typeOfQueryD.getResponseType().equals(STORAGE.get(i).getResponseType())
                    && equalDates(typeOfQueryD.getDates(), STORAGE.get(i).getDates())) {
                counter++;
                sum += STORAGE.get(i).getTime();
            }
        }
        if (counter == 0) {
            REPORT.add("-" + "\n");
        } else {
            REPORT.add(sum / counter + "\n");
        }
        return true;
    }

    public boolean equalTypeOfService(String[] typeOfQueryD, String[] typeOfQueryC) {
        if (typeOfQueryD[0].equals(ALL_MATCHES)) {
            return true;
        }
        int counter = 0;
        for (int i = 0; i < typeOfQueryD.length; i++) {
            try {
                if (typeOfQueryD[i].equals(typeOfQueryC[i])) {
                    counter++;
                }
            } catch (RuntimeException exception) {
                return false;
            }
        }
        return counter == typeOfQueryD.length;
    }

    public boolean equalTypeOfQuestion(String[] typeOfQueryD, String[] typeOfQueryC) {
        int counter = 0;
        for (int i = 0; i < typeOfQueryD.length; i++) {
            try {
                if (typeOfQueryD[i].equals(ALL_MATCHES)) {
                    return true;
                }
                if (typeOfQueryD[i].equals(typeOfQueryC[i])) {
                    counter++;
                }
            } catch (RuntimeException exception) {
                return false;
            }
        }
        return counter == typeOfQueryD.length;
    }

    public boolean equalDates(String[] typeOfQueryD, String[] typeOfQueryC) {
        if (typeOfQueryD[0].equals(ALL_MATCHES)) {
            return true;
        }
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern(DATE_FORMAT);
        if (typeOfQueryD.length > 1) {
            try {
                Date inDate = format.parse(typeOfQueryC[0]);
                Date beforeDate = format.parse(typeOfQueryD[0]);
                Date afterDate = format.parse(typeOfQueryD[1]);
                return inDate.after(beforeDate) && inDate.before(afterDate);
            } catch (ParseException exception) {
                throw new RuntimeException("Can't parse date to variable", exception);
            }
        } else {
            try {
                Date inDateTypeD = format.parse(typeOfQueryD[0]);
                Date inDateTypeC = format.parse(typeOfQueryC[0]);
                return inDateTypeC.equals(inDateTypeD);
            } catch (ParseException exception) {
                throw new RuntimeException("Can't parse date to variable", exception);
            }
        }
    }
}
