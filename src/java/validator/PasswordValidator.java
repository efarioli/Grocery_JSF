/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import javafx.css.StyleOrigin;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author f023507i
 */
@FacesValidator(value = "passwordValidator")
public class PasswordValidator implements Validator
        
{

    @Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {

		String password = value.toString();

		UIInput uiInputConfirmPassword = (UIInput) component.getAttributes()
				.get("confirmPassword");
                
		String confirmPassword = uiInputConfirmPassword.getSubmittedValue()
				.toString();

		// Let required="true" do its job.
		if (password == null || password.isEmpty() || confirmPassword == null
				|| confirmPassword.isEmpty()) {
			return;
		}

		if (!password.equals(confirmPassword)) {
			uiInputConfirmPassword.setValid(false);
			throw new ValidatorException(new FacesMessage(
					"Password must match confirm password."));
		}
                if (password.length()<3) {
			uiInputConfirmPassword.setValid(false);
			throw new ValidatorException(new FacesMessage(
					"Password must be at least 3 characters."));
		}

	}
    
}
