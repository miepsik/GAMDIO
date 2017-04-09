package biblioteka.forms;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import biblioteka.model.Author;
import biblioteka.services.BookService;

/**Validator for book addition form
 * @author Damian
 *
 */
@Component
public class BookCreateFormValidator implements Validator {

	private final BookService bookService;
	
	/**Constructor
	 * @param bookService
	 */
	@Autowired
	public BookCreateFormValidator(BookService bookService) {
		this.bookService = bookService;
	}

	/**Check if instance of BookCreateForm
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> arg0) {
		return arg0.equals(BookCreateForm.class);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		BookCreateForm form = (BookCreateForm) arg0;
		Collection<Author> authors = bookService.getAuthorsBySurname(form.getSurname());

		if(authors.size() > 1){
			for(Author a : authors){
				if(!a.getName().equals(form.getName())){
					authors.remove(a);
				}
			}
		}
		
		if(authors.size()>1){
			arg1.reject("authors", "Author is not identifiable.");
		}
	}

}
