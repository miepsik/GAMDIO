package biblioteka.services;

import biblioteka.model.Borrow;

public interface BorrowService {
	Borrow create(Long book, Long user);
	void finish(Long id);
}
