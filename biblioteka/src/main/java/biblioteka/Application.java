/**
 * 
 */
package biblioteka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**Main application
 * @author Damian
 *
 */

@SpringBootApplication
public class Application {

    /**Running application
     * @param args arguments from command line
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args); 
    }
}