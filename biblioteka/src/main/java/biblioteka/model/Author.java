/**
 * 
 */
package biblioteka.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**Model of author
 * @author Damian
 *
 */
@Entity
@Table(name="Autorzy")
public class Author {
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false, length = 11)
	private Long id;
	
	@Getter
	@Setter
	@Column(name = "imie", length = 25)
	private String name = "";
	
	@Getter
	@Setter
	@Column(name = "nazwisko", length = 25)
	private String surname = "";
	
	@Getter
	@Setter
	@OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Book> books = new HashSet<Book>();
	
	public void addBook(Book book){
		this.books.add(book);
		if(!book.getAuthor().equals(this)){
			book.setAuthor(this);
		}
	}
	
	public void removeBook(Book book){
		this.books.remove(book);
		if(!book.getAuthor().equals(this)){
			book.setAuthor(null);
		}
	}

	@Override
	public String toString() {
		return "Author [id=" + id + ", name=" + name + ", surname=" + surname + ", books=" + books.size() + "]";
	}
	
	
}
