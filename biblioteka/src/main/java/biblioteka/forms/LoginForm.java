package biblioteka.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class LoginForm {
	@NotNull
	@Size(min=2, max=30)
	@Getter
	@Setter
	String username;
	
	@NotNull
	@NotEmpty
	@Getter
	@Setter
    @Size(min=6, message="must be at least 6 characters")
    String password;
}
