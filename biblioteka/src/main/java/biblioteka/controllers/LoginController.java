package biblioteka.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import biblioteka.forms.LoginForm;

/**Controlling logging
 * @author Damian
 *
 */
@Controller
public class LoginController {
	/**Map when login page is requested with get
	 * @param model Model of page to be rendered
	 * @param error Potential error
	 * @return name of html to be rendered
	 */
	@GetMapping("/login")
	public String loginForm(Model model, @RequestParam Optional<String> error){
		model.addAttribute("form",new LoginForm());
		model.addAttribute("error", error);
		return "login";
	}
	
	/**Map when login page is requested with post (actions to be taken on submit)
	 * @param loginForm class used for controling logging form
	 * @param bindingResult class used for controlling errors
	 * @return name of html to be rendered
	 */
	@PostMapping("/login")
	public String loginSubmit(@Valid @ModelAttribute("form") LoginForm loginForm, BindingResult bindingResult){
		if(bindingResult.hasErrors())
			return "login";
		return "redirect:/";
	} 
	
}
