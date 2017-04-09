/**
 * 
 */
package biblioteka.model;

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
import lombok.ToString;

/**Model of author
 * @author Damian
 *
 */
@ToString
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
	@OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
	private Set<Book> books;
}
