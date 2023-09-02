package com.springboot.AsielBeheer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.Dier;
import jakarta.validation.Valid;
import repository.DierRepository;
import repository.ReservatieRepository;
import repository.VerblijfplaatsRepository;
import validator.AddDierValidation;

@Controller
@RequestMapping("/manage/addDier")
public class AddDierController {
	@Autowired
	private DierRepository dierRepository;
	@Autowired
	private ReservatieRepository reservatieRepository;
	@Autowired
	private VerblijfplaatsRepository verblijfplaatsRepository;
	 @Autowired
	private AddDierValidation addDierValidation;

	@GetMapping
	public String showHomePage(  Model model) {
		model.addAttribute("AddAnimal", new AddAnimal());
		return "addAnimal";
	}
	@PostMapping
	 //public String processRegistration(@Valid AddAnimal addAnimal, BindingResult result) {
	 public String processRegistration(@ModelAttribute("AddAnimal") @Valid AddAnimal addAnimal, BindingResult result) {
		
		addDierValidation.validate(addAnimal, result);
	        if (result.hasErrors()) {
		        System.out.println("Error");
	           return "addAnimal";
	        } 
	        System.out.println("Got here");
	        
			 
			
			 if(alreadyExists(addAnimal.getName()))
			 {
				  throw new Error("Animal with name " + addAnimal.getName() + " already exists");
			 }
			 else
			 { 
				 System.out.println("Got here #2");
				 Dier dier = new Dier(addAnimal.getName(), addAnimal.getRace(),addAnimal.getGender(), addAnimal.getBirthday(), addAnimal.getMedicalCosts(), addAnimal.isOkWithYoungKids(), addAnimal.isOkWithOlderKids(), addAnimal.isOkWithCats(), addAnimal.isOkWithDogs(), addAnimal.isOkAsIndoorCat(), false);
				 dierRepository.save(dier);
			 }
	        return "registrationSuccess";
	    }
	
	private boolean alreadyExists(String name)
	{
		List<Dier> dieren = dierRepository.getAllSorted();
		for(int i = 0; i < dieren.size(); i++)
		{
			if(dieren.get(i).getNaam().equals(name))
			{
				return true;
			}
		}
		return false;
		
	}
	
}
