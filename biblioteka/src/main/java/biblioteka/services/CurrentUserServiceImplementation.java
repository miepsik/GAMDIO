/**
 * 
 */
package biblioteka.services;

import org.springframework.stereotype.Service;

import biblioteka.model.Role;

/**
 * @author Damian
 *
 */
@Service
public class CurrentUserServiceImplementation implements CurrentUserService {

	/* (non-Javadoc)
	 * @see biblioteka.services.CurrentUserService#canAccessUser(biblioteka.services.CurrentUser, java.lang.Long)
	 */
	@Override
	public boolean canAccessUser(CurrentUser currentUser, Long userId) {
		return currentUser != null && (currentUser.getRole() == Role.ADMIN || currentUser.getId().equals(userId));
	}

}
