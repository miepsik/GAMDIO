package biblioteka.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController { 
	@ExceptionHandler(Exception.class)
	public ModelAndView handleError(HttpServletRequest req, Exception ex){
		
		ModelAndView mav = new ModelAndView("error");
		mav.addObject("url",req.getRequestURL());
		mav.addObject("exceptions", ex.getStackTrace());
		
		return mav;
	}
}
   