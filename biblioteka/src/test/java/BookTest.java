import biblioteka.model.Book;
import biblioteka.model.Position;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import static com.sun.xml.internal.ws.dump.LoggingDumpTube.Position.After;
import static com.sun.xml.internal.ws.dump.LoggingDumpTube.Position.Before;
import static org.junit.Assert.*;

public class BookTest {

    public Book book;
    Position mock;

    @Before
    public void setUp() throws Exception {
        book = new Book();
        mock = mock(Position.class);

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testAddPosition() throws Exception {
        when(mock.getBook()).thenReturn(book);
        book.addPosition(mock);
        assertEquals((int) book.getAmount(), 1);
    }

    @Test
    public void testRemovePosition() throws Exception {
        when(mock.getBook()).thenReturn(book);
        book.addPosition(mock);
        when(mock.getBook()).thenReturn(new Book());
        book.removePosition(mock);
        assertEquals((int) book.getAmount(), 0);
    }

    @Test
    public void testToString() throws Exception {
        book.setAmount(15);
        String s = book.toString();
        assertEquals(s, "Book [id=null, title=, category=, state=0, author=null, amount=15, borrows=0]");
    }

}