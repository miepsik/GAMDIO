package biblioteka.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	@Bean
    public SpringSecurityDialect securityDialect() {
        return new SpringSecurityDialect(); 
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
			.authorizeRequests()
				.antMatchers("/").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.failureUrl("/login?error")
				.permitAll()
				.and()
			.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/")
				.permitAll();
	}
	 
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
    }
}
