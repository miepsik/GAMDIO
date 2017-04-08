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
	@NotEmpty
	private String email = "";
	
	@Getter
	@Setter
	@NotEmpty
	private String password = "";
	
	@Getter
	@Setter
	@NotEmpty
	private String passwordRepeated = "";
	
	
	@Getter
	@Setter
	@NotNull
	private Role role = Role.ADMIN;
	//private Role role = Role.USER;
	
	
}
