/**
 * 
 */
package biblioteka.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**Allow every page access to current user details
 * @author Damian
 *
 */
@ControllerAdvice
public class CurrentUserControllerAdvice {
    /**Give every page access to current user details
     * @param authentication
     * @return user details
     */
    @ModelAttribute("currentUser")
    public UserDetails getCurrentUser(Authentication authentication) {
        return (authentication == null) ? null : (UserDetails) authentication.getPrincipal();
    }
}
