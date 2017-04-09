package biblioteka.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import biblioteka.forms.BookCreateForm;
import biblioteka.forms.BookCreateFormValidator;
import biblioteka.services.BookService;

/**Controlling pages concerning books
 * @author Damian
 *
 */
@Controller
public class BookController {
	
	private final BookService bookService;
	private final BookCreateFormValidator bookCreateFormValidator;
	
	
	/**Constructor
	 * @param bookService
	 * @param bookCreateFormValidator
	 */
	@Autowired
	public BookController(BookService bookService, BookCreateFormValidator bookCreateFormValidator) {
		this.bookService = bookService;
		this.bookCreateFormValidator = bookCreateFormValidator;
	}

	/**Adding userCreateFormValidator to data binder
	 * @param binder
	 */
	@InitBinder("form")
	public void initBinder(WebDataBinder binder){
		binder.addValidators(bookCreateFormValidator);
	}

	/**Controls rendering list of books
	 * @return
	 */
	@RequestMapping("/public/books")
	ModelAndView list(){
		ModelAndView mav = new ModelAndView("books");
		mav.addObject("books", bookService.getAllBooks());
		return mav; 
	}
	
	/**Control getting registration form
	 * @return model to be rendered
	 */
	@GetMapping(value = "/book/create")
	public ModelAndView getBookCreate(){
		ModelAndView mav = new ModelAndView("book_create");
		mav.addObject("form", new BookCreateForm());
		mav.addObject("action", "/book/create");
		return mav;
	}
	
	/**Control posting registration form
	 * @param form registration form
	 * @param bindingResult form validator
	 * @return html to be rendered
	 */
	@PostMapping(value = "/book/create") 
	public String handleBookCreation(@Valid @ModelAttribute("form") BookCreateForm form, BindingResult bindingResult){
		System.out.println("Ala ma kota");
		if(bindingResult.hasErrors()){
			return "book_create";
		}
		bookService.create(form);
		return "redirect:/login";
	}
}
 