package util.validator;

import java.util.regex.Pattern;

public class EmailValidator implements Validator {
    @Override
    public boolean isValid(String email){
        String emailRegex = "^(.+)@(.+).(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }
}
