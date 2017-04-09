package biblioteka.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**Controller of main page
 * @author Damian
 *
 */
@Controller
public class HomeController {
	/**Control main page
	 * @return html to be rendered
	 */
	@RequestMapping("/")
	String index(){
		return "index";
	}
}
//UserDetails currentUser = (UserDetails) authentication.getPrincipal();