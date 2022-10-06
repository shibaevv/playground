package au.com.totemsoft.crm.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import au.com.totemsoft.crm.model.ClientEntity;

public class ClientValidator implements ConstraintValidator<ClientValid, ClientEntity> {

    @Override
    public boolean isValid(ClientEntity value, ConstraintValidatorContext context) {
        // TODO Auto-generated method stub
        return false;
    }

}
