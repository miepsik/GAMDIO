package biblioteka.services;

import java.util.Collection;
import java.util.Optional;

import biblioteka.forms.BookCreateForm;
import biblioteka.model.Author;
import biblioteka.model.Book;

/**Interface describing accessing book table
 * @author Damian
 *
 */
public interface BookService {
	Optional<Book> getBookById(long id);
	Optional<Book> getBookByTitle(String title);
	Collection<Author> getAuthorsBySurname(String surname);
	Collection<Book> getBooksByAuthor(Author author);
	Collection<Book> getAllBooks();
	Book create(BookCreateForm form);
	Book update(BookCreateForm form, Long id);
	void delete(Long id);
}
