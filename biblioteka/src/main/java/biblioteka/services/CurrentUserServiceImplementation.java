/**
 * 
 */
package biblioteka.services;

import org.springframework.stereotype.Service;

import biblioteka.model.Role;

/**Interface allowing checking if page can be accessed by user ofr admin
 * @author Damian
 *
 */
@Service
public class CurrentUserServiceImplementation implements CurrentUserService {

	/**Check if page can be accessed if current role
	 * @see biblioteka.services.CurrentUserService#canAccessUser(biblioteka.services.CurrentUser, java.lang.Long)
	 */
	@Override
	public boolean canAccessUser(CurrentUser currentUser, Long userId) {
		return currentUser != null && (currentUser.getRole() == Role.ADMIN || currentUser.getId().equals(userId));
	}

}
