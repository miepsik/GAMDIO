/**
 * 
 */
package biblioteka.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import biblioteka.forms.PositionCreateForm;
import biblioteka.model.BookRepository;
import biblioteka.model.Position;
import biblioteka.model.PositionRepository;

/**
 * @author Damian
 *
 */
@Service
public class PositionServiceImplemented implements PositionService {
	
	BookRepository br;
	PositionRepository pr;
	
	/**Constructor
	 * @param br book repository
	 * @param pr position repository
	 */
	@Autowired
	public PositionServiceImplemented(BookRepository br, PositionRepository pr) {
		this.br = br;
		this.pr = pr;
	}

	/** Create new position
	 * 
	 */
	@Override
	public Position create(PositionCreateForm form, Long id) {
		Position p = new Position();

		p.setSection(form.getSection());
		p.setShelf(form.getShelf());
		p.setBook(br.getOne(id));
		System.out.println("ala5\n\n");
		pr.save(p);
		System.out.println("ala6\n\n");
		return p;
	}

	/** Delete position
	 * 
	 */
	@Override
	public void delete(Long id) {
		Position p = pr.getOne(id);
		
		p.getBook().removePosition(p);
		
		pr.delete(p);
	}

	/** Get copies that take the same place
	 * 
	 */
	@Override
	public Collection<Position> getCopiesByShelfAndSection(Long shelf, String section) {
		return pr.findAllByShelfAndSection(shelf, section);
	}

	/** Get book instances
	 * 
	 */
	@Override
	public Collection<Position> getAllCopiesById(Long id) {
		return pr.findAllByBook(br.getOne(id));
	}

	
}
