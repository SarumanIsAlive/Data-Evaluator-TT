package service.handler;

import dto.QueryDto;
import storage.Storage;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GetOperation implements OperationHandler {
    private static final String DATE_FORMAT = "dd.MM.yyyy";

    @Override
    public boolean apply(QueryDto typeOfQueryD) {
        int counter = 0;
        int sum = 0;
        for (int i = 0; i < Storage.storage.size(); i++) {
            if (equalTypeOfService(typeOfQueryD.getService(), Storage.storage.get(i).getService())
                    && equalTypeOfQuestion(typeOfQueryD.getQuestion(), Storage.storage.get(i).getQuestion())
                    && typeOfQueryD.getResponseType().equals(Storage.storage.get(i).getResponseType())
                    && equalDates(typeOfQueryD.getDates(), Storage.storage.get(i).getDates())) {
                counter++;
                sum += Storage.storage.get(i).getTime();
            }
        }
        if (counter == 0) {
            Storage.report.add("-" + "\n");
        } else {
            Storage.report.add(sum / counter + "\n");
        }
        return true;
    }

    public boolean equalTypeOfService(String[] typeOfQueryD, String[] typeOfQueryC) {
        if (typeOfQueryD[0].equals("*")) {
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
                if (typeOfQueryD[i].equals("*")) {
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
        if (typeOfQueryD[0].equals("*")) {
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
                throw new RuntimeException("Cant parse data!", exception);
            }
        } else {
            try {
                Date inDateD = format.parse(typeOfQueryD[0]);
                Date inDateC = format.parse(typeOfQueryC[0]);
                return inDateC.equals(inDateD);
            } catch (ParseException exception) {
                throw new RuntimeException("Cant parse data!", exception);
            }
        }
    }
}
