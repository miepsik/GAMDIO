/**
 * 
 */
package biblioteka.services;

import org.springframework.security.core.authority.AuthorityUtils;

import biblioteka.model.Role;
import biblioteka.model.User;
import lombok.Getter;

/**
 * @author Damian
 *
 */
public class CurrentUser extends org.springframework.security.core.userdetails.User {
	private static final long serialVersionUID = 1L;
	@Getter
	private User user;
	
	public CurrentUser(User user){
		super(user.getEmail(),user.getPasswordHash(),AuthorityUtils.createAuthorityList(user.getRole().toString()));
		this.user = user;
	}
	
	public Long getId(){
		return user.getId();
	}
	
	public Role getRole(){
		return user.getRole();
	}
}
