package in.employeeManagement.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import in.employeeManagement.controller.EmployeeController;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(SecurityConfig.class);
	
	@Override
    protected void configure(HttpSecurity http) throws Exception 
    {
		LOGGER.info("Inside Configure method : Authorisation ");
        http
         .csrf().disable()
         .authorizeRequests().anyRequest().authenticated()
         .and()
         .httpBasic();
    }
  
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) 
            throws Exception 
    {
    	LOGGER.info("Inside ConfigureGlobal method : Authorisation Credential ");
        auth.inMemoryAuthentication()
            .withUser("User")
            .password("{noop}password")
            .roles("USER");
    }
}
