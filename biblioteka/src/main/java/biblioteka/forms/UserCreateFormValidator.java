package biblioteka.forms;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import biblioteka.services.UserService;

@Component
public class UserCreateFormValidator implements Validator {

	private final UserService userService;
	
	/**
	 * @param userService
	 */
	public UserCreateFormValidator(UserService userService) {
		this.userService = userService;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return arg0.equals(UserCreateForm.class);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		UserCreateForm form = (UserCreateForm) arg0;
		if(!form.getPassword().equals(form.getPasswordRepeated()))
			arg1.reject("password", "Passwords do not match.");
		if(userService.getUserByEmail(form.getEmail()).isPresent())
			arg1.reject("email", "User with this email already exists");
	}
}
