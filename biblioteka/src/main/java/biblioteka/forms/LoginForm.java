package biblioteka.forms;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class LoginForm {
	@NotNull(message="E-mail field cannot be null")
	@Getter
	@Setter
	String email;
	
	@NotNull
	@NotEmpty(message="Password cannot be empty")
	@Getter
	@Setter
    String password;
}
