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
import domain.Reservatie;
import domain.Verblijfplaats;
import jakarta.validation.Valid;
import repository.DierRepository;
import repository.ReservatieRepository;
import repository.VerblijfplaatsRepository;
import validator.AddDierValidation;

@Controller
@RequestMapping("/dieren/reservaties")
public class ReserveerController {
	@Autowired
	private DierRepository dierRepository;
	@Autowired
	private ReservatieRepository reservatieRepository;
	@Autowired
	private VerblijfplaatsRepository verblijfplaatsRepository;
	@Autowired
	private AddDierValidation addDierValidation;

	@GetMapping
	public String showHomePage(Model model) {
		List<Reservatie> reservaties = (List<Reservatie>) reservatieRepository.findAll();
		model.addAttribute("reservaties", reservaties);
		return "reservaties";
	}

	
}
