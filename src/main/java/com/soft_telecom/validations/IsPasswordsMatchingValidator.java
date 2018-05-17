package com.soft_telecom.validations;


import com.soft_telecom.areas.users.models.bindingModels.UserRegistrationModel;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsPasswordsMatchingValidator implements ConstraintValidator<IsPasswordsMatching, Object> {
    @Override
    public void initialize(IsPasswordsMatching isPasswordsMatching) {

    }

    @Override
    public boolean isValid(Object userClass, ConstraintValidatorContext constraintValidatorContext) {

        if(userClass instanceof UserRegistrationModel){
            String password = ((UserRegistrationModel) userClass).getPassword();
            String confirmedPassword = ((UserRegistrationModel) userClass).getConfirmPassword();
            return password.equals(confirmedPassword);
        }

        return false;
    }
}
