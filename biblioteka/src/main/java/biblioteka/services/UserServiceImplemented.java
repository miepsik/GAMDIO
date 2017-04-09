package biblioteka.services;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import biblioteka.forms.UserCreateForm;
import biblioteka.model.User;
import biblioteka.model.UserRepository;

/**Controlling accessing user repository
 * @author Damian
 *
 */
@Service
public class UserServiceImplemented implements UserService {

	UserRepository userRepository;
		
	/**Constructor
	 * @param userRepository
	 */
	public UserServiceImplemented(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	/** Returns potential user given id
	 * @see biblioteka.services.UserService#getUserById(long)
	 */
	@Override
	public Optional<User> getUserById(long id) {
		return Optional.ofNullable(userRepository.findOne(id));
	}

	/** Returns potential user given email
	 * @see biblioteka.services.UserService#getUserByEmail(java.lang.String)
	 */
	@Override
	public Optional<User> getUserByEmail(String email) {
		return userRepository.findOneByEmail(email);
	}

	/** Returns potential users sorted by email
	 * @see biblioteka.services.UserService#getAllUsers()
	 */
	@Override
	public Collection<User> getAllUsers() {
		return userRepository.findAll(new Sort("email"));
	}

	/**Create new user given registration form
	 * @see biblioteka.services.UserService#create(biblioteka.forms.UserCreateForm)
	 */
	@Override
	public User create(UserCreateForm form) {
		User user = new User();
		user.setEmail(form.getEmail());
		user.setPasswordHash(new BCryptPasswordEncoder().encode(form.getPassword()));
		user.setRole(form.getRole());
		return userRepository.save(user);
	}

	/** Update user given form and id
	 * @see biblioteka.services.UserService#update(biblioteka.forms.UserCreateForm, java.lang.Long)
	 */
	@Override
	public User update(UserCreateForm form, Long id) {
		User user = userRepository.getOne(id);
		user.setEmail(form.getEmail());
		user.setPasswordHash(new BCryptPasswordEncoder().encode(form.getPassword()));
		user.setRole(form.getRole());
		return userRepository.save(user);
	}

	/** Delete user given id
	 * @see biblioteka.services.UserService#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) {
		userRepository.delete(id);
	}

}
