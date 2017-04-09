/**
 * 
 */
package biblioteka.model;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

/**Makes accessing functionality of JpaRepository available
 * @author Damian
 *
 */
public interface AuthorRepository extends JpaRepository<Author, Long> {
	Collection<Author> findAllBySurname(String surname);
}
