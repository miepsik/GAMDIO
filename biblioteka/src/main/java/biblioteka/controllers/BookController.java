package biblioteka.controllers;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**Controlling pages concerning books
 * @author Damian
 *
 */
@Controller
public class BookController {
	private class Book{ 
		@SuppressWarnings("unused")
		public String name = "Ala ma kota";
	}
	/**Controlls rendering list of books
	 * @return
	 */
	@RequestMapping("/books")
	ModelAndView list(){
		ModelAndView mav = new ModelAndView("books");
		ArrayList<Book> lista = new ArrayList<Book>();
		lista.add(new Book());
		lista.add(new Book());
		lista.add(new Book());
		lista.add(new Book());
		lista.add(new Book());
		mav.addObject("books", lista);
		return mav; 
	}
}
 