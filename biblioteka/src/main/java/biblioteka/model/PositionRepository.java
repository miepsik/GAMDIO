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
public interface PositionRepository extends JpaRepository<Position, Long> {
	Collection<Position> findAllByShelfAndSection(Long shelf,String section);
	Collection<Position> findAllByBook(Book book);
}
