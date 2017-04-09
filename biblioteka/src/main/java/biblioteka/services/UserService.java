package biblioteka.services;

import java.util.Collection;
import java.util.Optional;

import biblioteka.forms.UserCreateForm;
import biblioteka.model.User;

/**Interface describing accessing user table
 * @author Damian
 *
 */
public interface UserService {
	Optional<User> getUserById(long id);
	Optional<User> getUserByEmail(String email);
	Collection<User> getAllUsers();
	User create(UserCreateForm form);
	User update(UserCreateForm form, Long id);
	void delete(Long id);
}
