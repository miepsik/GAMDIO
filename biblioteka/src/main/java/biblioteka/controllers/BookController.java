package biblioteka.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import biblioteka.forms.BookCreateForm;
import biblioteka.forms.BookCreateFormValidator;
import biblioteka.model.Book;
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

	/**Adding bookCreateFormValidator to data binder
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
		if(bindingResult.hasErrors()){
			return "book_create";
		}
		bookService.create(form);
		return "redirect:/public/books";
	}
	
	/**Control getting book edition form
	 * @param id id of book to be edited
	 * @return model to be rendered
	 */
	@GetMapping("/book/update/{id}")
	public ModelAndView editBook(@PathVariable Long id){
		ModelAndView mav = new ModelAndView("book_create");
		BookCreateForm bookCreateForm = new BookCreateForm();
		Book book = bookService.getBookById(id).get();

		bookCreateForm.setCategory(book.getCategory());
		bookCreateForm.setState(book.getState());
		bookCreateForm.setTitle(book.getTitle());
		bookCreateForm.setName(book.getAuthor().getName());
		bookCreateForm.setSurname(book.getAuthor().getSurname());

		mav.addObject("form", bookCreateForm);
		mav.addObject("action", "/book/update/"+id);
		return mav;
	}
	
	/**Control posting book edition form
	 * @param id id of book to be edited
	 * @param form form to be used for edition
	 * @param bindingResult form validator
	 * @return model to be rendered
	 */
	@PostMapping("/book/update/{id}")
	public String handleBookEdition(@PathVariable Long id, @Valid @ModelAttribute("form") BookCreateForm form, BindingResult bindingResult){		
		if(bindingResult.hasErrors()){
			return "redirect:/book/update/"+id;
		}
		bookService.update(form,id);
		return "redirect:/";
	}
	
	/**Control deleting book
	 * @param id id of book to be deleted
	 * @return html to be rendered
	 */
	@RequestMapping("/book/delete/{id}")
	public String deleteBook(@PathVariable Long id){
		bookService.delete(id);
		return "redirect:/public/books";
	}
}
 