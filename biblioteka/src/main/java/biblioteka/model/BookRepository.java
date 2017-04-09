/**
 * 
 */
package biblioteka.model;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

/**Makes accessing functionality of JpaRepository available
 * @author Damian
 *
 */
public interface BookRepository extends JpaRepository<Book, Long> {
	Optional<Book> findOneByTitle(String title);
	Collection<Book> findAllByAuthor(Author author);
}
