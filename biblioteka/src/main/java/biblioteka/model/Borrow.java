package biblioteka.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="Wypozyczenia")
public class Borrow {
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false, length = 11)
	private Long id;
	
	@Getter
	@Setter
	@Column(name="dataStart")
	private Date dateStart = new Date((new java.util.Date()).getTime());
	
	@Getter
	@Setter
	@Column(name="dataEnd")
	private Date dateEnd = null;
	
	@Getter
	@Setter
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDPozycji", nullable=false)
	private Position position;
	
	@Getter
	@Setter
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDUser", nullable=false)
	private User user;
}
