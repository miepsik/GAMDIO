package biblioteka.forms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import biblioteka.services.UserService;

/**Validator for registration form
 * @author Damian
 *
 */
@Component
public class UserCreateFormValidator implements Validator {

	private final UserService userService;
	
	/**Constructor
	 * @param userService injected service controlling accesses to database concerning user
	 */
	@Autowired
	public UserCreateFormValidator(UserService userService) {
		this.userService = userService;
	}

	/**Check if instance of UserCreateForm
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> arg0) {
		return arg0.equals(UserCreateForm.class);
	}

	/**Validate instance of user creation form
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object arg0, Errors arg1) {
		UserCreateForm form = (UserCreateForm) arg0;
		if(!form.getPassword().equals(form.getPasswordRepeated()))
			arg1.reject("password", "Passwords do not match.");
		if(userService.getUserByEmail(form.getEmail()).isPresent())
			arg1.reject("email", "User with this email already exists");
	}
}
