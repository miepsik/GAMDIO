/**
 * 
 */
package biblioteka.services;

/**Interface allowing checking if page can be accessed by user ofr admin
 * @author Damian
 *
 */
public interface CurrentUserService {
	public boolean canAccessUser(CurrentUser currentUser, Long userId);
}
