package com.springboot.AsielBeheer;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import validator.AddDierValidation;

@Import(SecurityConfig.class)
@SpringBootApplication
@EnableJpaRepositories("repository")
@EntityScan("domain")
public class OpdrachtEwdj20222023ThomasLelong1AsielBeheerApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(OpdrachtEwdj20222023ThomasLelong1AsielBeheerApplication.class, args);
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/", "/dieren");
	    registry.addViewController("/403").setViewName("403");
	}

	@Bean
	AddDierValidation registrationValidation() {
		return new AddDierValidation();
	}

	@Bean
	LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.ENGLISH);
		return slr;
	}
}
