package biblioteka.model;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class AuthorTest {

    Author author;
    Book book;
    @Before
    public void setUp() throws Exception {
        author = new Author();
        book = mock(Book.class);
    }

    @Test
    public void testAddBook() throws Exception {
        when(book.getAuthor()).thenReturn(author);
        author.addBook(book);
        assertEquals((int) author.getBooks().size(), 1);
        verify(book, times(0)).setAuthor(isA(Author.class));
    }

    @Test
    public void testAddBook1() throws Exception {
        when(book.getAuthor()).thenReturn(new Author());
        author.addBook(book);
        assertEquals((int) author.getBooks().size(), 1);
        verify(book, times(1)).setAuthor(isA(Author.class));
    }

    @Test
    public void testToString() throws Exception {
        String s = author.toString();
        assertEquals(s, "Author [id=null, name=, surname=, #books=0]");
    }

    @Test
    public void testToString1() throws Exception {
        when(book.getAuthor()).thenReturn(new Author());
        author.addBook(book);
        String s = author.toString();
        assertEquals(s, "Author [id=null, name=, surname=, #books=1]");
    }

}