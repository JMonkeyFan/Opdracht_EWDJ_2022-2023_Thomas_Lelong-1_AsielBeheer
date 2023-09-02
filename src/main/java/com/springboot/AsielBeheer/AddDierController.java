package com.springboot.AsielBeheer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



import domain.Dier;
import domain.Reservatie;
import jakarta.validation.Valid;
import repository.DierRepository;
import repository.ReservatieRepository;
import repository.VerblijfplaatsRepository;

@Controller
@RequestMapping("/manage")
public class AddDierController {
	@Autowired
	private DierRepository dierRepository;
	@Autowired
	private ReservatieRepository reservatieRepository;
	@Autowired
	private VerblijfplaatsRepository verblijfplaatsRepository;
	
	@GetMapping(value="/addDier")
	public String addAnimal(  Model model) {
		model.addAttribute("AddAnimal", new AddAnimal());
		return "addAnimal";
	}
	@PostMapping
	 //public String processRegistration(@Valid AddAnimal addAnimal, BindingResult result) {
	 public String processRegistration(AddAnimal addAnimal, BindingResult result) {
	        //registrationValidation.validate(registration, result);
	        /*if (result.hasErrors()) {
	            return "registrationForm";
	        } * 
	 */
		 System.out.println("Got here");
		 Dier dier = new Dier(addAnimal.getName(), addAnimal.getRace(),addAnimal.getGender(), addAnimal.getBirthday(), addAnimal.getMedicalCosts(), addAnimal.isOkWithYoungKids(), addAnimal.isOkWithOlderKids(), addAnimal.isOkWithCats(), addAnimal.isOkWithDogs(), addAnimal.isOkAsIndoorCat(), false);
		 dierRepository.save(dier);
	        return "registrationSuccess";
	    }
	
	
}
