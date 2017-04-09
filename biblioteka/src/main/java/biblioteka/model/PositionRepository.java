/**
 * 
 */
package biblioteka.model;

import org.springframework.data.jpa.repository.JpaRepository;

/**Makes accessing functionality of JpaRepository available
 * @author Damian
 *
 */
public interface PositionRepository extends JpaRepository<Position, Long> {

}
