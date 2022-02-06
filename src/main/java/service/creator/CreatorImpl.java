package service.creator;

import dto.QueryDto;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreatorImpl implements Creator {
    private static final String DATE_FORMAT = "dd.MM.yyyy";
    private static final String TYPE_OF_LINE_C = "C";
    private static final String TYPE_OF_LINE_D = "D";

    @Override
    public List<String> createReport(List<QueryDto> dtoList) {
        int counter = 0;
        int sum = 0;
        List<String> report = new ArrayList<>();
        for (int i = 0; i < dtoList.size(); i++) {
            if (dtoList.get(i).getTypeOfQuery().equals(TYPE_OF_LINE_D)) {
                counter = 0;
                sum = 0;
                for (int j = 0; j < i; j++) {
                   if (equalTypeOfService(dtoList.get(i).getService(), dtoList.get(j).getService())
                           && equalTypeOfQuestion(dtoList.get(i).getQuestion(), dtoList.get(j).getQuestion())
                           && dtoList.get(i).getResponseType().equals(dtoList.get(j).getResponseType())
                           && equalDates(dtoList.get(i).getDates(), dtoList.get(j).getDates())
                           && dtoList.get(j).getTypeOfQuery().equals(TYPE_OF_LINE_C)) {
                       counter++;
                       sum += dtoList.get(j).getTime();
                   }
                }
                if (counter == 0) {
                    report.add("-" + "\n");
                } else {
                    report.add(sum / counter + "\n");
                }
            }
        }
        return report;
    }

    public boolean equalTypeOfService(String[] typeOfQueryD, String[] typeOfQueryC) {
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
