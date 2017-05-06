import biblioteka.model.Author;
import biblioteka.model.Book;
import biblioteka.model.Position;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

import static org.mockito.Mockito.*;
import static com.sun.xml.internal.ws.dump.LoggingDumpTube.Position.After;
import static com.sun.xml.internal.ws.dump.LoggingDumpTube.Position.Before;
import static org.junit.Assert.*;

public class BookTest {

    public Book book;
    Position mock;
    Author author;

    @Before
    public void setUp() throws Exception {
        book = new Book();
        mock = mock(Position.class);
        author = mock(Author.class);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testAddPosition() throws Exception {
        when(mock.getBook()).thenReturn(book);
        book.addPosition(mock);
        assertEquals((int) book.getAmount(), 1);
        verify(mock, times(0)).setBook(isA(Book.class));
    }

    @Test
    public void testAddPosition1() throws Exception {
        when(mock.getBook()).thenReturn(new Book());
        book.addPosition(mock);
        assertEquals((int) book.getAmount(), 1);
        verify(mock, times(1)).setBook(isA(Book.class));
    }

    @Test
    public void testRemovePosition() throws Exception {
        when(mock.getBook()).thenReturn(book);
        book.addPosition(mock);
        verify(mock, times(0)).setBook(isA(Book.class));
        when(mock.getBook()).thenReturn(new Book());
        book.removePosition(mock);
        verify(mock, times(0)).setBook(isA(Book.class));
        assertEquals((int) book.getAmount(), 0);
    }

    @Test
    public void testRemovePosition1() throws Exception {
        when(mock.getBook()).thenReturn(new Book());
        book.addPosition(mock);
        verify(mock, times(1)).setBook(isA(Book.class));
        when(mock.getBook()).thenReturn(new Book());
        book.removePosition(mock);
        assertEquals((int) book.getAmount(), 0);
        verify(mock, times(1)).setBook(isA(Book.class));
    }

    @Test
    public void testSetAuthor() throws Exception {
        HashSet<Book> hs = new HashSet<>();
        hs.add(book);
        when(author.getBooks()).thenReturn(hs);
        book.setAuthor(author);
        verify(author, times(0)).addBook(isA(Book.class));
    }

    @Test
    public void testSetAuthor1() throws Exception {
        HashSet<Book> hs = new HashSet<>();
        hs.add(new Book());
        when(author.getBooks()).thenReturn(hs);
        book.setAuthor(author);
        verify(author, times(1)).addBook(isA(Book.class));
    }


    @Test
    public void testToString() throws Exception {
        book.setAmount(15);
        String s = book.toString();
        assertEquals(s, "Book [id=null, title=, category=, state=0, author=null, amount=15, borrows=0]");
    }

}