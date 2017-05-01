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
	@NotNull
	@Digits(fraction = 0, integer = 11)
	Long shelf = new Long(0L);
	
	@Getter
	@Setter
	@NotEmpty
	@NotNull
	String section = "";
}
