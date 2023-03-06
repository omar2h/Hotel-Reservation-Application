package util.validator;

import java.util.regex.Pattern;

public class StringValidator implements Validator {
    @Override
    public boolean isValid(String s, String regex){
        // String emailRegex = "^(.+)@(.+).(.+)$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(s).matches();
    }
}
