package biblioteka.forms;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import biblioteka.model.Position;
import biblioteka.services.PositionService;

/**Validator for position addition form
 * @author Damian
 *
 */
@Component
public class PositionCreateFormValidator implements Validator {

	private final PositionService bookService;
	
	/**Constructor
	 * @param bookService
	 */
	@Autowired
	public PositionCreateFormValidator(PositionService bookService) {
		this.bookService = bookService;
	}

	/**Check if instance of BookCreateForm
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> arg0) {
		return arg0.equals(PositionCreateForm.class);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		PositionCreateForm form = (PositionCreateForm) arg0;
		Collection<Position> placeTakers = bookService.getCopiesByShelfAndSection(form.getShelf(), form.getSection());
		if(placeTakers.size()>0)
			arg1.reject("placeTaken", "This position is taken.");
	}

}
