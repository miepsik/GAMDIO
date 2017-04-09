package biblioteka.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**Control error page
 * @author Damian
 *
 */
@Controller
@ControllerAdvice
public class CustomErrorController implements ErrorController{ 
	
	private static final String PATH = "/error";
	
	/**Do something with errors
	 * @param req http servlet request
	 * @param res http servlet response
	 * @param ex exception
	 * @return model and view
	 */
	@RequestMapping(PATH)
	@ExceptionHandler(value = Exception.class)
	public ModelAndView handleError(HttpServletRequest req, HttpServletResponse res, Exception ex){
		ModelAndView mav = new ModelAndView("error");
		mav.addObject("url",req.getRequestURL());
		mav.addObject("exceptions", ex.getStackTrace());
		mav.addObject("status",res.getStatus());
		return mav;
	}

	/** return path to error page
	 * @see org.springframework.boot.autoconfigure.web.ErrorController#getErrorPath()
	 */
	@Override
	public String getErrorPath() {
		return PATH;
	}
}
   