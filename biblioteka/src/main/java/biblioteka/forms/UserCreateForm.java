package biblioteka.forms;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import biblioteka.model.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**Class for registration
 * @author Damian
 *
 */
@ToString
public class UserCreateForm {
	@Getter
	@Setter
	@NotEmpty(message="E-mail field cannot be empty")
	private String email = "";
	
	@Getter
	@Setter
	@NotEmpty(message="Password field cannot be empty")
	private String password = "";
	
	@Getter
	@Setter
	@NotEmpty(message="You have to repeat the password")
	private String passwordRepeated = "";
	
	
	@Getter
	@Setter
	@NotNull(message="Role field cannot be null")
	private Role role = Role.ADMIN;
	//private Role role = Role.USER;
	
	
}
