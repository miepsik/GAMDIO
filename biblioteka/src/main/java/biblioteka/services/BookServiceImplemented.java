/**
 * 
 */
package biblioteka.services;

import java.util.Collection;
import java.util.HashSet;
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
		Collection<Author> authors = authorRepo.findAllBySurname(form.getSurname());
		
		if(authors.size() > 1){
			for(Author a : authors){
				if(!a.getName().equals(form.getName())){
					authors.remove(a);
				}
			}
		}
		
		Book book = new Book();
		book.setTitle(form.getTitle());
		book.setCategory(form.getCategory());
		book.setState(form.getState());
		
		Author author;
		if(authors.isEmpty()){
			author = new Author();
			author.setName(form.getName());
			author.setSurname(form.getSurname());
			author.setBooks(new HashSet<Book>());
			authorRepo.save(author);
		} else if(authors.size() == 1){
			author = authors.iterator().next();		
		} else {
			return null;
		}
		
		book.setAuthor(author);
		bookRepo.save(book);
		
		return book;
	}

	/* (non-Javadoc)
	 * @see biblioteka.services.BookService#update(biblioteka.forms.BookCreateForm, java.lang.Long)
	 */
	@Override
	public Book update(BookCreateForm form, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	/**Delete book given id and if there is no other books written by this author delete him too
	 * @see biblioteka.services.BookService#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) {
		Author author = bookRepo.getOne(id).getAuthor();
		if(author.getBooks().size() == 1){
			authorRepo.delete(author);
		}
		bookRepo.delete(id);
	}

	@Override
	public Collection<Author> getAuthorsBySurname(String surname) {
		return authorRepo.findAllBySurname(surname);
	}

}
