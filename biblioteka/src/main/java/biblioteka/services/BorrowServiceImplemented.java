package biblioteka.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import biblioteka.model.Borrow;
import biblioteka.model.BorrowRepository;
import biblioteka.model.PositionRepository;
import biblioteka.model.UserRepository;

public class BorrowServiceImplemented implements BorrowService {

	PositionRepository ps;
	UserRepository us;
	BorrowRepository bs;
	
	/**
	 * @param ps
	 * @param us
	 * @param bs
	 */
	@Autowired
	public BorrowServiceImplemented(PositionRepository ps, UserRepository us, BorrowRepository bs) {
		this.ps = ps;
		this.us = us;
		this.bs = bs;
	}

	@Override
	public Borrow create(Long book, Long user) {
		Borrow b = new Borrow();
		b.setPosition(ps.getOne(book));
		b.setUser(us.getOne(user));
		bs.save(b);
		return b;
	}

	@Override
	public void finish(Long id) {
		Borrow b = bs.getOne(id);
		b.setDateEnd((java.sql.Date)new Date());
		bs.save(b);
	}

}
