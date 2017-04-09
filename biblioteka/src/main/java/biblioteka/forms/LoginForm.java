package biblioteka.forms;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class LoginForm {
	@NotNull
	@Getter
	@Setter
	String email;
	
	@NotNull
	@NotEmpty
	@Getter
	@Setter
    String password;
}
