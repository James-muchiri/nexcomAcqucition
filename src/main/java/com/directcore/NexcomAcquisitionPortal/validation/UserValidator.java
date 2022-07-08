package com.directcore.NexcomAcquisitionPortal.validation;


import com.directcore.NexcomAcquisitionPortal.model.Admi;
import com.directcore.NexcomAcquisitionPortal.repositories.AdmiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Component
public class UserValidator implements Validator {
    @Autowired
    private AdmiRepository admiRepository;
    @Override
    public boolean supports(Class<?> aClass) {
        return Admi.class.equals(aClass);
    }
    private Pattern regexPattern;
    private Matcher regMatcher;
    @Override
    public void validate(Object o, Errors errors) {
        Admi user = (Admi) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
        regexPattern = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
        regMatcher   = regexPattern.matcher(user.getEmail());
        if (!regMatcher.matches()) {
            errors.rejectValue("email", "Valid.userForm.email");
        }
        if (admiRepository.findByEmail(user.getEmail()) != null) {
            errors.rejectValue("email", "Duplicate.userForm.email");
        }
     /**   regexPattern = Pattern.compile("^\\+[0-9]{2,3}+-[0-9]{10}$");
        regMatcher   = regexPattern.matcher(mobileNumber);
        if(regMatcher.matches()) {
            return "Valid Mobile Number";
        } else {
            return "Invalid Mobile Number";
        }
      **/
      ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
      //  if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
       //     errors.rejectValue("password", "Size.userForm.password");
     //   }

      //  if (!user.getPasswordConfirm().equals(user.getPassword())) {
      //      errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
       // }

    }



    public void validateLogin(String email, String password, Errors errors) {


        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
        regexPattern = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
        regMatcher   = regexPattern.matcher(email);
        if (!regMatcher.matches()) {
            errors.rejectValue("email", "Valid.userForm.email");
        }
        if (admiRepository.findByEmail(email) == null) {
            errors.rejectValue("email", "Notexist.userForm.email");
        }



    }
}
