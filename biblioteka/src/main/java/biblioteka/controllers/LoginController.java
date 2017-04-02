package biblioteka.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import biblioteka.forms.LoginForm;

@Controller
public class LoginController {
	@GetMapping("/login")
	public String loginForm(Model model){
		model.addAttribute("form",new LoginForm());
		return "login";
	}
	
	@PostMapping("/login")
	public String loginSubmit(@Valid LoginForm loginForm, BindingResult bindingResult){
		//TODO login submit
		System.out.println(loginForm.toString());
		if(bindingResult.hasErrors())
			return "login";
		return "redirect:/";
	} 
}
