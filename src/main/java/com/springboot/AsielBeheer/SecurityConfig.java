package com.springboot.AsielBeheer;

import javax.sql.DataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	DataSource dataSource;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().and().authorizeHttpRequests(requests -> requests.requestMatchers("/login**").permitAll()
				.requestMatchers("/css/**").permitAll().requestMatchers("/403**").permitAll()
				.requestMatchers("/*").access(new WebExpressionAuthorizationManager("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')"))
				.requestMatchers("/dieren")
				.access(new WebExpressionAuthorizationManager("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')"))
				.requestMatchers("/reservaties")
				.access(new WebExpressionAuthorizationManager("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')"))
				.requestMatchers("/dieren/*")
				.access(new WebExpressionAuthorizationManager("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')"))
				.requestMatchers("/dieren/rassen/*")
				.access(new WebExpressionAuthorizationManager("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')"))
				.requestMatchers("/dieren/reserveer/*")
				.access(new WebExpressionAuthorizationManager("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')"))
				.requestMatchers("/manage/addDier")
				.access(new WebExpressionAuthorizationManager("hasAnyRole('ROLE_ADMIN')")))
				// of .hasRole("USER"))
				.formLogin(form -> form.defaultSuccessUrl("/dieren", true).loginPage("/login")
						.usernameParameter("username").passwordParameter("password"))
				.exceptionHandling().accessDeniedPage("/403");

		return http.build();
	}

	/*
	 * @Bean SecurityFilterChain securityFilterChain(HttpSecurity http) throws
	 * Exception { http.csrf().and() .authorizeHttpRequests(requests ->
	 * requests.requestMatchers("/login**").permitAll()
	 * .requestMatchers("/css/**").permitAll()
	 * .requestMatchers("/403**").permitAll() .requestMatchers("/*").access(new
	 * WebExpressionAuthorizationManager("hasRole('ROLE_USER')"))) //of
	 * .hasRole("USER")) .formLogin(form -> form.defaultSuccessUrl("/dieren", true)
	 * .loginPage("/login")
	 * .usernameParameter("username").passwordParameter("password"))
	 * .exceptionHandling().accessDeniedPage("/403");
	 * 
	 * return http.build(); }
	 * 
	 */
}