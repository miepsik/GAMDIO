package biblioteka.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

public class LoginForm {
	@NotNull
	@Size(min=2, max=30)
	String username;
	
	@NotNull
	@NotEmpty
    @Size(min=6, message="must be at least 6 characters")
    String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginForm [username=" + username + ", password=" + password + "]";
	}
}
