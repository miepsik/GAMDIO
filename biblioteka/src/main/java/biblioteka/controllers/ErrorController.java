package biblioteka.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;


@ControllerAdvice
public class ErrorController { 
	 
	@ExceptionHandler(value = Exception.class)
	public ModelAndView handleError(HttpServletRequest req, HttpServletResponse res, Exception ex){
		System.out.println("Ala am kota");
		ModelAndView mav = new ModelAndView("error");
		mav.addObject("url",req.getRequestURL());
		mav.addObject("exceptions", ex.getStackTrace());
		mav.addObject("status",res.getStatus());
		
		return mav;
	}
}
   