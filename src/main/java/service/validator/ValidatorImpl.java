package service.validator;

public class ValidatorImpl implements Validator {
    private static final String REGEX_SPACE = " ";
    private static final String TYPE_OF_LINE_C = "C";
    private static final String TYPE_OF_LINE_D = "D";
    private static final String TYPE_OF_RESPONSE_P = "P";
    private static final String TYPE_OF_RESPONSE_N = "N";
    private static final int MIN_DATE_LENGTH = 10;

    @Override
    public boolean isValid(String line) {
        String[] lineArr = line.split(REGEX_SPACE);
        return lineArr[1].length() >= 1 && lineArr[1].length() <= 4
                && lineArr[2].length() >= 1 && lineArr[2].length() <= 7
                && (lineArr[3].equals(TYPE_OF_RESPONSE_P) || lineArr[3].equals(TYPE_OF_RESPONSE_N))
                && lineArr[4].length() >= MIN_DATE_LENGTH && line.length() > 0
                && ((lineArr.length <= 6 && lineArr[0].equals(TYPE_OF_LINE_C))
                || (lineArr.length <= 5 && lineArr[0].equals(TYPE_OF_LINE_D)));
    }
}
