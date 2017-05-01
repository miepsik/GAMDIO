package biblioteka.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**Model of book
 * @author Damian
 *
 */
//TODO indeksy na idautora i tytul
@Entity
@Table(name = "Ksiazki")
public class Book {
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false, length = 20)
	private Long id;
	
	@Getter
	@Setter
	@Column(name = "tytul", length = 50)
	private String title = "";
	
	@Getter
	@Setter
	@Column(name = "kategoria", length = 10)
	private String category = "";
	
	@Getter
	@Setter
	@Column(name = "stan", length = 11)
	private Integer state = 0;
	
	@Getter
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "IDAutora", nullable = false)
	private Author author;
	
	@Getter
	@Setter
	@Column(name = "ilosc", length = 11)
	private Integer amount = 0;
	
	@Getter
	@Setter
	@Column(name = "wypozyczenia", length = 11)
	private Integer borrows = 0;

	public void setAuthor(Author author) {
		this.author = author;
		if(!author.getBooks().contains(this)){
			author.addBook(this);
		}
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", category=" + category + ", state=" + state + ", author="
				+ author + ", amount=" + amount + ", borrows=" + borrows + "]";
	}
	
	
}
