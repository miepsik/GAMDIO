package biblioteka;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.junit.runners.MethodSorters;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.junit.Test;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AcceptanceTests {
	private WebDriver driver;
	  private String baseUrl;
	  private boolean acceptNextAlert = true;
	  private StringBuffer verificationErrors = new StringBuffer();

	  @Before
	  public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "chromedriver_win32.exe");
		
		driver = new ChromeDriver();
		baseUrl = "http://localhost:8080";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }

	  @Test
	  public void test1RegisterOK() throws Exception {
	    driver.get(baseUrl + "/");
	    driver.findElement(By.linkText("Register")).click();
	    driver.findElement(By.id("email")).clear();
	    driver.findElement(By.id("email")).sendKeys("test@put.io");
	    driver.findElement(By.id("password")).clear();
	    driver.findElement(By.id("password")).sendKeys("test");
	    driver.findElement(By.id("passwordRepeated")).clear();
	    driver.findElement(By.id("passwordRepeated")).sendKeys("test");
	    driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
	    assertEquals("Remember me", driver.findElement(By.cssSelector("div.checkbox > label")).getText());
	  }

	  @Test
	  public void test2RegisterFAIL() throws Exception {
		driver.get(baseUrl + "/");
		driver.findElement(By.linkText("Register")).click();
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("test@put.io");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("test");
		driver.findElement(By.id("passwordRepeated")).clear();
		driver.findElement(By.id("passwordRepeated")).sendKeys("test");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("test");
		assertEquals("Warning! User with this email already exists", driver.findElement(By.cssSelector("li.alert.alert-danger")).getText());
	  }
	  
	  @Test
	  public void test3LoginFAIL() throws Exception {
	    driver.get(baseUrl + "/");
	    driver.findElement(By.linkText("Log in")).click();
	    driver.findElement(By.id("email")).clear();
	    driver.findElement(By.id("email")).sendKeys("test@put.io");
	    driver.findElement(By.id("password")).clear();
	    driver.findElement(By.id("password")).sendKeys("test1");
	    driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
	    assertEquals("Remember me", driver.findElement(By.cssSelector("div.checkbox > label")).getText());
	  }
	  
	  @Test
	  public void test4LoginOK() throws Exception {
	    driver.get(baseUrl + "/");
	    driver.findElement(By.linkText("Log in")).click();
	    driver.findElement(By.id("email")).clear();
	    driver.findElement(By.id("email")).sendKeys("test@put.io");
	    driver.findElement(By.id("password")).clear();
	    driver.findElement(By.id("password")).sendKeys("test");
	    driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
	    assertEquals("Logged in as test@put.io", driver.findElement(By.cssSelector("p.navbar-text.navbar-right")).getText());
	    driver.findElement(By.linkText("Log out")).click();
	  }
	  
	  @Test
	  public void test5AddBook() throws Exception {
	    driver.get(baseUrl + "/");
	    driver.findElement(By.linkText("Log in")).click();
	    driver.findElement(By.id("email")).clear();
	    driver.findElement(By.id("email")).sendKeys("test@put.io");
	    driver.findElement(By.id("password")).clear();
	    driver.findElement(By.id("password")).sendKeys("test");
	    driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
	    driver.findElement(By.linkText("Available actions")).click();
	    driver.findElement(By.linkText("Search library")).click();
	    driver.findElement(By.linkText("Add Book")).click();
	    driver.findElement(By.id("title")).clear();
	    driver.findElement(By.id("title")).sendKeys("Test");
	    driver.findElement(By.id("category")).clear();
	    driver.findElement(By.id("category")).sendKeys("test");
	    driver.findElement(By.id("name")).clear();
	    driver.findElement(By.id("name")).sendKeys("test");
	    driver.findElement(By.id("surname")).clear();
	    driver.findElement(By.id("surname")).sendKeys("test");
	    driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
	    assertEquals("Test", driver.findElement(By.xpath("//table/tbody/tr[td//text()[contains(., 'test test')]]/td[1]")).getText());
	    driver.findElement(By.linkText("Log out")).click();
	  }
	  
	  @Test
	  public void test6CleanUp() throws Exception {
		driver.get(baseUrl + "/");
		driver.findElement(By.linkText("Log in")).click();
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("test@put.io");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("test");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		driver.findElement(By.linkText("Available actions")).click();
		driver.findElement(By.linkText("Search library")).click();
		driver.findElement(By.xpath("//table/tbody/tr[td[a[text()[contains(., 'Test')]]] and td[text()[contains(., 'test test')]]]/td[last()]/a")).click();
		driver.findElement(By.linkText("Available actions")).click();
		driver.findElement(By.linkText("List users")).click();
		driver.findElement(By.xpath("//table/tbody/tr[td[text()='test@put.io']]/td[last()]/a")).click();
		driver.findElement(By.linkText("Log out")).click();
	  }
	  
	  @After
	  public void tearDown() throws Exception {
	    driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	  }
}
