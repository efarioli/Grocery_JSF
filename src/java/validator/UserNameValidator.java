/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import dbase.UserGateway;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author f023507i
 */
@FacesValidator(value = "userNameValidator")

public class UserNameValidator implements Validator
{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException
    {
        UserGateway ug = new UserGateway();
        String userName = (String) value;
        boolean isUserinDB = ug.isUsernameInDB(userName);
        if (isUserinDB)
        {
            //component.setValid(false);
            throw new ValidatorException(new FacesMessage(
                    "Username already exist"));
        } else if(userName.length()<3 || userName.length()>20){
            throw new ValidatorException(new FacesMessage(
                    "Username must be at least 3, and cannot exceed 20 characters"));
        }
    }

}
