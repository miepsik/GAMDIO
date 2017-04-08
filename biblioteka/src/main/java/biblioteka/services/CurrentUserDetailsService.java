/**
 * 
 */
package biblioteka.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import biblioteka.model.User;

/**
 * @author Damian
 *
 */
@Service
public class CurrentUserDetailsService implements UserDetailsService {
	private final UserService userService;

	/**
	 * @param userService
	 */
	@Autowired
	public CurrentUserDetailsService(UserService userService) {
		this.userService = userService;
	}



	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
		User user = userService.getUserByEmail(arg0)
				.orElseThrow(() -> new UsernameNotFoundException(String.format("User with email=%s was not found", arg0)));
		return new CurrentUser(user);
	}

}
