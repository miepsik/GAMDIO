package biblioteka.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@ControllerAdvice
public class CustomErrorController implements ErrorController{ 
	
	private static final String PATH = "/error";
	
	@RequestMapping("/error")
	@ExceptionHandler(value = Exception.class)
	public ModelAndView handleError(HttpServletRequest req, HttpServletResponse res, Exception ex){
		System.out.println("Ala am kota");
		ModelAndView mav = new ModelAndView("error");
		mav.addObject("url",req.getRequestURL());
		mav.addObject("exceptions", ex.getStackTrace());
		mav.addObject("status",res.getStatus());
		
		return mav;
	}

	@Override
	public String getErrorPath() {
		return PATH;
	}
}
   