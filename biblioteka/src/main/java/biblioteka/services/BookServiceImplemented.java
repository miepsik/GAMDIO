/**
 * 
 */
package biblioteka.services;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import biblioteka.forms.BookCreateForm;
import biblioteka.model.Author;
import biblioteka.model.AuthorRepository;
import biblioteka.model.Book;
import biblioteka.model.BookRepository;

/**Controlling accessing book repository
 * @author Damian
 *
 */
@Service
public class BookServiceImplemented implements BookService {

	BookRepository bookRepo;
	AuthorRepository authorRepo;
	
	/**Constructor
	 * @param bookRepo
	 * @param authorRepo
	 */
	@Autowired
	public BookServiceImplemented(BookRepository bookRepo, AuthorRepository authorRepo) {
		this.bookRepo = bookRepo;
		this.authorRepo = authorRepo;
	}

	/** Returns potential book given id
	 * @see biblioteka.services.BookService#getBookById(long)
	 */
	@Override
	public Optional<Book> getBookById(long id) {
		return Optional.ofNullable(bookRepo.findOne(id));
	}

	/** Returns potential book given title
	 * @see biblioteka.services.BookService#getBookByTitle(java.lang.String)
	 */
	@Override
	public Optional<Book> getBookByTitle(String title) {
		return bookRepo.findOneByTitle(title);
	}

	/** Returns potential books given author
	 * @see biblioteka.services.BookService#getBookByAuthor(biblioteka.model.Author)
	 */
	@Override
	public Collection<Book> getBooksByAuthor(Author author) {
		return bookRepo.findAllByAuthor(author);
	}

	/** Returns all books sorted by title
	 * @see biblioteka.services.BookService#getAllBooks()
	 */
	@Override
	public Collection<Book> getAllBooks() {
		return bookRepo.findAll(new Sort("title"));
	}

	/**Create new book given form
	 * @throws Exception in case there is more than one author
	 * @see biblioteka.services.BookService#create(biblioteka.forms.BookCreateForm)
	 */
	@Override
	public Book create(BookCreateForm form){
		Collection<Author> authors = authorRepo.findAllBySurnameAndName(form.getSurname(),form.getName());
		
		Book book = new Book();
		book.setTitle(form.getTitle());
		book.setCategory(form.getCategory());
		book.setState(form.getState());
		
		Author author;
		if(authors.isEmpty()){
			author = new Author();
			author.setName(form.getName());
			author.setSurname(form.getSurname());
		} else if(authors.size() == 1){
			author = authors.iterator().next();		
		} else {
			return null;
		}
		book.setAuthor(author);
		
		authorRepo.save(author);
		//bookRepo.save(book);
				
		return book;
	}

	/** Edit book given form
	 * @see biblioteka.services.BookService#update(biblioteka.forms.BookCreateForm, java.lang.Long)
	 */
	@Override
	public Book update(BookCreateForm form, Long id) {
		Book book = bookRepo.getOne(id);
		book.setTitle(form.getTitle());
		book.setCategory(form.getCategory());
		book.setState(form.getState());
		
		if(!(book.getAuthor().getSurname().equals(form.getSurname()) && book.getAuthor().getName().equals(form.getName()))){
			Collection<Author> authors = authorRepo.findAllBySurnameAndName(form.getSurname(),form.getName());
			Collection<Author> authors2 = authorRepo.findAllBySurnameAndName(book.getAuthor().getSurname(),book.getAuthor().getName());
			Author author;
			if(authors.isEmpty()){
				author = new Author();
				author.setName(form.getName());
				author.setSurname(form.getSurname());
			} else {//if(authors.size() == 1){
				author = authors.iterator().next();		
			}
			
			book.setAuthor(author);			
			authorRepo.save(author);
			
			if(authors2.size()>0){
				author = authors2.iterator().next();
				if(author.getBooks().size()<=1){
					authorRepo.delete(author);
				}
			}
		}
		System.out.println(book);
		bookRepo.save(book);
		return null;
	}

	/**Delete book given id and if there is no other books written by this author delete him too
	 * @see biblioteka.services.BookService#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) {
		Book book = bookRepo.getOne(id);
		bookRepo.delete(book);
		System.out.println(book.getAuthor().getBooks().size());
		if(book.getAuthor().getBooks().size()<2){
			Author author = book.getAuthor();
			authorRepo.delete(author);
		}
	}

	@Override
	public Collection<Author> getAuthorsBySurnameAndName(String surname, String name) {
		return authorRepo.findAllBySurnameAndName(surname,name);
	}

}
