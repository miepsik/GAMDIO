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

import biblioteka.forms.PositionCreateForm;
import biblioteka.forms.PositionCreateFormValidator;
import biblioteka.services.PositionService;

/**Controlling adding and removing copies (instances) of books
 * @author Damian
 *
 */
@Controller
public class PositionController {
	private final PositionService ps;
	private final PositionCreateFormValidator pcfv;
	
	/**Constructor
	 * @param ps service
	 * @param pcfv form validator
	 */
	@Autowired
	public PositionController(PositionService ps, PositionCreateFormValidator pcfv) {
		this.ps = ps;
		this.pcfv = pcfv;
	}
	
	/**Adding validator to data binder
	 * @param binder
	 */
	@InitBinder("form")
	public void initBinder(WebDataBinder binder){
		binder.addValidators(pcfv);
	}
	
	@RequestMapping("/copies/{id}")
	public ModelAndView listCopies(@PathVariable Long id){
		ModelAndView mav = new ModelAndView("copies");
		mav.addObject("copies", ps.getAllCopiesById(id));
		mav.addObject("book_id", id);
		return mav;
	}
	
	/**Add copy view controller
	 * @param id id of book
	 * @return
	 */
	@GetMapping(value="/copies/add/{id}")
	public ModelAndView addCopy(@PathVariable Long id){
		ModelAndView mav = new ModelAndView("add_copy");
		mav.addObject("form", new PositionCreateForm());
		return mav;
	}
	
	/**Handle adding new instance of book
	 * @param id
	 * @param form
	 * @param bindingResult
	 * @return
	 */
	@PostMapping(value="/copies/add/{id}")
	public String handleAddingCopy(@PathVariable Long id, @Valid @ModelAttribute("form") PositionCreateForm form, BindingResult bindingResult){
		System.out.println("ala\n\n");
		if(bindingResult.hasErrors()){
			return "redirect:/copies/"+id;
		}
		System.out.println("ala\n\n");
		ps.create(form,id);
		System.out.println("ala\n\n");
		return "redirect:/";
	}
	
	/**Handle deleting a copy
	 * @param id id of book
	 * @param copy_id id of instance
	 * @return redirect to list of copies
	 */
	@RequestMapping("/copies/delete/{id}/{copy_id}")
	public String deleteCopy(@PathVariable Long id, @PathVariable Long copy_id){
		ps.delete(copy_id);
		return "redirect:/copies/"+id;
	}
}
