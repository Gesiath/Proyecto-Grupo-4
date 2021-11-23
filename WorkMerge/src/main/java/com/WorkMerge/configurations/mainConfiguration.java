package com.WorkMerge.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.WorkMerge.services.AdminService;
import com.WorkMerge.services.ClientService;
import com.WorkMerge.services.CompanyService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true) //Permite actualizar la url

public class mainConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	private ClientService clientService;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private AdminService adminService;
	
	//Metodo para autenticacion
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(clientService).passwordEncoder(new BCryptPasswordEncoder());
		auth.userDetailsService(companyService).passwordEncoder(new BCryptPasswordEncoder());
		auth.userDetailsService(adminService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	//Configuracion de las peticiones http
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/css/*", "/js/*", "/img/*", "/**").permitAll()
			.and().formLogin()
				.loginPage("/login")
				.usernameParameter("mail")
				.passwordParameter("password")
				.defaultSuccessUrl("/")
				.loginProcessingUrl("/logincheck")
				.failureUrl("/login?error=error")
				.permitAll()
			.and().logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/login?logout")
			.and().csrf().disable();
			
	}
}