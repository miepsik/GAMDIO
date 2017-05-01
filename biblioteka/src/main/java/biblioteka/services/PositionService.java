package biblioteka.services;

import java.util.Collection;

import biblioteka.forms.PositionCreateForm;
import biblioteka.model.Position;

/**Interface describing accessing position table
 * @author Damian
 *
 */
public interface PositionService {
	/*
	Optional<Book> getBookById(long id);
	Optional<Book> getBookByTitle(String title);
	Collection<Book> getBooksByAuthor(Author author);
	Collection<Book> getAllBooks();
	Book update(BookCreateForm form, Long id);
*/
	Position create(PositionCreateForm form, Long id);
	void delete(Long id);
	
	Collection<Position> getAllCopiesById(Long id);
	Collection<Position> getCopiesByShelfAndSection(Long shelf,String section);
}
