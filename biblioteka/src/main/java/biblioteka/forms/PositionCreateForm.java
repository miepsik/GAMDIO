/**
 * 
 */
package biblioteka.forms;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**Form to create instances of books
 * @author Damian
 *
 */
@ToString
public class PositionCreateForm {
	@Getter
	@Setter
	@NotNull(message="Shelf field cannot be null")
	@Digits(fraction = 0, integer = 11)
	Long shelf = new Long(0L);
	
	@Getter
	@Setter
	@NotEmpty(message="Section field cannot be empty")
	@NotNull
	String section = "";
}
