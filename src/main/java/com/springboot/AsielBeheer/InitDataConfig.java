package com.springboot.AsielBeheer;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import domain.Dier;
import domain.Reservatie;
import domain.Verblijfplaats;
import repository.DierRepository;
import repository.ReservatieRepository;
import repository.VerblijfplaatsRepository;

@Component
public class InitDataConfig implements CommandLineRunner {

	@Autowired
	private DierRepository dierRepository;
	@Autowired
	private VerblijfplaatsRepository verblijfplaatsRepository;
	@Autowired
	private ReservatieRepository reservatieRepository;

	@Override
	public void run(String... args) {
		/*
		 * String naam, String ras, String geslacht, LocalDate geboorteDatum, double medischeKosten,
		boolean kanMetJongeKinderen, boolean kanMetOudereKinderen, boolean kanMetKatten, boolean kanMetHonden,
		boolean geschiktAlsBinnenkat, boolean reedsGereserveerd
		 */ 
		Dier Jack = new Dier("Jack","BLA5000002", "Border Collie", "Mannelijk", LocalDate.parse("2018-12-27"), 107.25, false, true, false, true, false, false);
		Dier Jones = new Dier("Jones","BLA5000002", "Collie", "Mannelijk", LocalDate.parse("2018-12-27"), 251.7, false, false, false, true, false, false);
		Dier Jill = new Dier("Jill","BLA5000002", "Labrador", "Vrouwelijk", LocalDate.parse("2018-12-27"), 13.833, true, true, true, true, false, false);
		Dier Rocky = new Dier("Rocky","BLA5000002", "Boxer", "Mannelijk", LocalDate.parse("2018-12-27"), 150.53, false, true, false, false, false, false);
		Dier SirKittensTheThird = new Dier("Sir Kittens the third","BLA5000002", "Abbysinian", "Vrouwelijk", LocalDate.parse("2018-12-27"), 5000.45, false, true, false, true, true, false);
		Verblijfplaats Palace = new Verblijfplaats(1,6,"Palace");
		Verblijfplaats TenWoofingStreet = new Verblijfplaats(2,5,"10 Woofing Street");
		Verblijfplaats CasaDelBark = new Verblijfplaats(3,4,"Casa del bark");
		Verblijfplaats BarktonAvenue = new Verblijfplaats(4,3,"Barkton avenue");
		Verblijfplaats BorkiPark = new Verblijfplaats(5,2,"Borki Park");
		Verblijfplaats Barkville = new Verblijfplaats(6,1,"Barkville");
		Barkville.voegToe(Jack); 
		BorkiPark.voegToe(Jones); 
		BarktonAvenue.voegToe(Jill); 
		CasaDelBark.voegToe(Jack); 
		TenWoofingStreet.voegToe(Rocky); 
		Palace.voegToe(SirKittensTheThird); 
		dierRepository.save(Jack);
		dierRepository.save(Jones);
		dierRepository.save(Jill);
		dierRepository.save(Rocky);
		dierRepository.save(SirKittensTheThird);
	
		verblijfplaatsRepository.save(Palace);
		verblijfplaatsRepository.save(TenWoofingStreet);
		verblijfplaatsRepository.save(CasaDelBark);
		verblijfplaatsRepository.save(BarktonAvenue);
		verblijfplaatsRepository.save(BorkiPark);
		verblijfplaatsRepository.save(Barkville);
		Reservatie reservatieJack = new Reservatie(Jones, "Jeff");
		reservatieRepository.save(reservatieJack);
		dierRepository.save(Jack);
		System.out.println(dierRepository.getAllSorted());
		
		
	}

}
