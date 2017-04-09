package biblioteka.model;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

/**Makes accessing functionality of JpaRepository available
 * @author Damian
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**Allows selecting by email
     * @param email email
     * @return selected user or "null"
     */
    Optional<User> findOneByEmail(String email);
}
