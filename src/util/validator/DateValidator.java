package util.validator;

import java.util.regex.Pattern;

public class DateValidator implements Validator {
    @Override
    public boolean isValid(String date, String dateRegex) throws IllegalArgumentException{
        Pattern pattern = Pattern.compile(dateRegex);
        if(!pattern.matcher(date).matches())
            return false;
        String[] tokens = date.split("/");
        int[] intTokens = new int[3];
        for(int i=0; i<3; i++){
            int n = Integer.parseInt(tokens[i]);
            intTokens[i] = n;
        }
        if(tokens.length != 3)
            throw new IllegalArgumentException("Enter date in this format dd/mm/yyyy. Example 01/01/2020");
        if(intTokens[0] < 1 || intTokens[0] > 31)
            throw new IllegalArgumentException("Invalid day. Enter day between 1-31");
        if(intTokens[1] < 1 || intTokens[1] > 12)
            throw new IllegalArgumentException("Invalid month. Enter month between 1-12");

        return true;
    }
}
