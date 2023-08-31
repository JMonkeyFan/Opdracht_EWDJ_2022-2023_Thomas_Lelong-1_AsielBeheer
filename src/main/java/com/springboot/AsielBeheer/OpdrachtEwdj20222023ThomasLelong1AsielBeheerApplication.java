package com.springboot.AsielBeheer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
@EnableJpaRepositories("repository")
@EntityScan("domain")
public class OpdrachtEwdj20222023ThomasLelong1AsielBeheerApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(OpdrachtEwdj20222023ThomasLelong1AsielBeheerApplication.class, args);
	}

	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
	   registry.addRedirectViewController("/", "/guest");
    }

}
