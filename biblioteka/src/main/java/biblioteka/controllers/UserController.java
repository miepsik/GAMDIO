package biblioteka.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import biblioteka.forms.UserCreateForm;
import biblioteka.forms.UserCreateFormValidator;
import biblioteka.model.User;
import biblioteka.services.UserService;

/**Controlling pagges concerning users
 * @author Damian
 *
 */
@Controller
public class UserController {
	
	private final UserService userService;
	private final UserCreateFormValidator userCreateFormValidator;
	
	
	/**Constructor
	 * @param userService class controlling accesses to database concerning users
	 * @param userCreateFormValidator class validating registration form
	 */
	@Autowired
	public UserController(UserService userService, UserCreateFormValidator userCreateFormValidator) {
		this.userService = userService;
		this.userCreateFormValidator = userCreateFormValidator;
	}
	
	/**Adding userCreateFormValidator to data binder
	 * @param binder
	 */
	@InitBinder("form")
	public void initBinder(WebDataBinder binder){
		binder.addValidators(userCreateFormValidator);
	}

	/**Control listing users
	 * @return html to be rendered
	 */
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping("/users")
	public ModelAndView list(){
		ModelAndView mav = new ModelAndView("users");
		mav.addObject("users", userService.getAllUsers());
		return mav; 
	}
	
	/**Control getting user edition form
	 * @param id id of user to be edited
	 * @return model to be rendered
	 */
	@PreAuthorize("@currentUserServiceImplementation.canAccessUser(principal,#id)")
	@GetMapping("/user/{id}")
	public ModelAndView editUser(@PathVariable Long id){
		ModelAndView mav = new ModelAndView("user_create");
		UserCreateForm userCreateForm = new UserCreateForm();
		User user = userService.getUserById(id).get();
		userCreateForm.setEmail(user.getEmail());
		mav.addObject("form", userCreateForm);
		mav.addObject("action", "/user/"+id);
		return mav;
	}
	
	/**Control posting user edition form
	 * @param id id of user to be edited
	 * @param form form to be used for edition
	 * @param bindingResult form validator
	 * @return model to be rendered
	 */
	@PreAuthorize("@currentUserServiceImplementation.canAccessUser(principal,#id)")
	@PostMapping("/user/{id}")
	public String handleUserEdition(@PathVariable Long id, @Valid @ModelAttribute("form") UserCreateForm form, BindingResult bindingResult){		
		if(bindingResult.hasErrors()){
			return "redirect:/user/"+id;
		}
		userService.update(form,id);
		return "redirect:/";//TODO USER -> / ADMIN -> users
	}
	
	/**Control getting registration form
	 * @return model to be rendered
	 */
	@GetMapping(value = "/public/create")
	public ModelAndView getUserCreate(){
		ModelAndView mav = new ModelAndView("user_create");
		mav.addObject("form", new UserCreateForm());
		mav.addObject("action", "/public/create");
		return mav;
	}
	
	/**Control posting registration form
	 * @param form registration form
	 * @param bindingResult form validator
	 * @return html to be rendered
	 */
	@PostMapping(value = "/public/create") 
	public String handleUserCreation(@Valid @ModelAttribute("form") UserCreateForm form, BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			return "user_create";
		}
		userService.create(form);
		return "redirect:/login";
	}	
	
	/**Control deleting user
	 * @param id id of user to be deleted
	 * @return html to be rendered
	 */
	@PreAuthorize("@currentUserServiceImplementation.canAccessUser(principal,#id)")
	@RequestMapping("/deleteuser/{id}")
	public String deleteUser(@PathVariable Long id){
		userService.delete(id);
		return "redirect:/users";
	}
}

