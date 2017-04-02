package biblioteka.controllers;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BookController {
	private class Book{ 
		public String name = "Ala ma kota";
	}
	@RequestMapping("/listBooks")
	ModelAndView list(){
		ModelAndView mav = new ModelAndView("listBooks");
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
 