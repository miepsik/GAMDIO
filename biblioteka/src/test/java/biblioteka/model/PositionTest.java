package biblioteka.model;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class PositionTest {
    Position position;
    Book book;

    @Before
    public void setUp() throws Exception {
        position = new Position();
        book = mock(Book.class);
    }

    @Test
    public void testSetBook() throws Exception {
        HashSet books = new HashSet();
        books.add(position);
        when(book.getCopies()).thenReturn(books);
        position.setBook(book);
        assertEquals((Book) position.getBook(), book);
        verify(book, times(0)).addPosition(isA(Position.class));
    }

    @Test
    public void testSetBook1() throws Exception {
        HashSet books = new HashSet();
        books.add(new Book());
        when(book.getCopies()).thenReturn(books);
        position.setBook(book);
        assertEquals((Book) position.getBook(), book);
        verify(book, times(1)).addPosition(isA(Position.class));
    }

}