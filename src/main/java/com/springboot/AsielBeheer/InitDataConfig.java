package com.springboot.AsielBeheer;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import domain.Dier;
import repository.DierRepository;

@Component
public class InitDataConfig implements CommandLineRunner {

	@Autowired
	private DierRepository repository;

	@Override
	public void run(String... args) {
		/*
		 * String naam, String ras, String geslacht, LocalDate geboorteDatum, double medischeKosten,
		boolean kanMetJongeKinderen, boolean kanMetOudereKinderen, boolean kanMetKatten, boolean kanMetHonden,
		boolean geschiktAlsBinnenkat, boolean reedsGereserveerd
		 */ 
		repository.save(new Dier("Jack", "Border Collie", "Mannelijk", LocalDate.parse("2018-12-27"), 107.25, false, true, false, true, false, true));
		repository.save(new Dier("Jones", "Collie", "Mannelijk", LocalDate.parse("2018-12-27"), 251.7, false, false, false, true, false, false));
		repository.save(new Dier("Jill", "Labrador", "Vrouwelijk", LocalDate.parse("2018-12-27"), 13.833, true, true, true, true, false, false));
		repository.save(new Dier("Rocky", "Boxer", "Mannelijk", LocalDate.parse("2018-12-27"), 150.53, false, true, false, false, false, false));
		repository.save(new Dier("Sir kittens the third", "Abbysinian", "Vrouwelijk", LocalDate.parse("2018-12-27"), 5000.45, false, true, false, true, true, true));
	}

}
