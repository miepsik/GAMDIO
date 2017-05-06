package bilbioteka.controllers;

import biblioteka.controllers.BookController;
import biblioteka.forms.BookCreateForm;
import biblioteka.model.Author;
import biblioteka.model.Book;
import biblioteka.services.BookService;
import static java.util.Arrays.asList;
import java.util.List;
import java.util.Optional;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.Matchers.hasProperty;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.View;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



public class BookControllerTest {
    @Mock
    BookService mockBookService;
         
    @InjectMocks
    BookController controller;
    
    @Mock
    View mockView;
    
    @Mock
    Book mockBook;
    
    @Mock
    Author mockAuthor;
    
    MockMvc mockMvc;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).setSingleView(mockView).build();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void listTest() throws Exception {
        List<Book> expectedBooks = asList(new Book());
        when(mockBookService.getAllBooks()).thenReturn(expectedBooks);
        
        mockMvc.perform(get("/public/books")).andExpect(status().isOk()).andExpect(model().attribute("books", expectedBooks)).andExpect(view().name("books"));
    }
    
    @Test
    public void getBookCreateTest() throws Exception {
        mockMvc.perform(get("/book/create")).andExpect(status().isOk()).andExpect(model().attribute("form", isA(BookCreateForm.class))).andExpect(model().attribute("action", "/book/create"));
    }
    
    @Test
    public void editBookTest() throws Exception {
        when(mockBook.getTitle()).thenReturn("IO2 jest super");
        when(mockBook.getAuthor()).thenReturn(mockAuthor);
        when(mockBookService.getBookById(1)).thenReturn(Optional.of(mockBook));
        
        mockMvc.perform(get("/book/update/1")).andExpect(status().isOk()).andExpect(model().attribute("form", isA(BookCreateForm.class))).andExpect(model().attribute("form", hasProperty("title", equalTo("IO2 jest super")))).andExpect(model().attribute("action", "/book/update/1")).andExpect(view().name("book_create"));
        verify(mockBook, times(1)).getTitle();
        verify(mockBook, times(2)).getAuthor();
        verify(mockBookService, times(1)).getBookById(1);
    }
    
    @Test
    public void deleteTest1() throws Exception {
        mockMvc.perform(get("/book/delete/1")).andExpect(status().isOk()).andExpect(view().name("redirect:/public/books"));
        verify(mockBookService, times(1)).delete(1L);
    }
    
    @Test
    public void deleteTest2() throws Exception {
        mockMvc.perform(post("/book/delete/1")).andExpect(status().isOk()).andExpect(view().name("redirect:/public/books"));
        verify(mockBookService, times(1)).delete(1L);
    }    
}
    