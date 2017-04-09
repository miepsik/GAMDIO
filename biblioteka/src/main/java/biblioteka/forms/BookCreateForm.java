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
	@NotEmpty
	@NotNull
	private String title = "";
	
	@Getter
	@Setter
	@NotEmpty
	@NotNull
	private String category = "";
	
	@Getter
	@Setter
	@NotNull
	@Digits(fraction = 0, integer = 11)
	private Integer state = 0;
	
	@Getter
	@Setter
	@NotEmpty
	@NotNull
	private String name = "";
	
	@Getter
	@Setter
	@NotEmpty
	@NotNull
	private String surname = "";
}
