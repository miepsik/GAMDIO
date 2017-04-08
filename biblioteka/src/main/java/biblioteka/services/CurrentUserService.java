/**
 * 
 */
package biblioteka.services;

/**
 * @author Damian
 *
 */
public interface CurrentUserService {
	public boolean canAccessUser(CurrentUser currentUser, Long userId);
}
