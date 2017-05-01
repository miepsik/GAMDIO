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
import lombok.ToString;

/**Model of books position
 * @author Damian
 *
 */
//TODO indeksy na idksiazki
@ToString
@Entity
@Table(name="pozycje")
public class Position {
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false, length = 20)
	private Long id;
	
	@Getter
	@Setter
	@Column(name = "polka", length = 11)
	private Long shelf = new Long(0L);
	
	@Getter
	@Setter
	@Column(name = "dzial", length = 3)
	private String section = "";
	
	@Getter
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDKsiazki",nullable = false)
	Book book;

	public void setBook(Book book2) {
		this.book = book2;
		if(!book2.getCopies().contains(this)){
			book2.addPosition(this);
		}
	}
	
	@Getter
	@Setter
	@Column(name = "dostepnosc", length = 1)
	private int availability = 0;
}
