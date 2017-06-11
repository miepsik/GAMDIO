package biblioteka.forms;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class BookCreateForm {
	@Getter
	@Setter
	@NotEmpty(message="Title field cannot be empty!")
        @NotNull
	private String title = "";
	
	@Getter
	@Setter
	@NotEmpty(message="Category field cannot be empty")
        @NotNull
	private String category = "";
	
	@Getter
	@Setter
	@NotNull(message="State field cannot be null")
	@Digits(fraction = 0, integer = 11)
	private Integer state = 0;
	
	@Getter
	@Setter
	@NotEmpty(message="Author's name cannot be empty")
	@NotNull
	private String name = "";
	
	@Getter
	@Setter
	@NotEmpty(message="Author's surname cannot be empty")
	@NotNull
	private String surname = "";
}
