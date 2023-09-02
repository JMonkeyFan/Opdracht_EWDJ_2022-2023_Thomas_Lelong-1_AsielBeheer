package com.springboot.AsielBeheer;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.Reservatie;
import repository.DierRepository;
import repository.ReservatieRepository;
import repository.VerblijfplaatsRepository;
import validator.AddDierValidation;

@Controller
@RequestMapping("/reservaties")
public class ReservatieController {
	@Autowired
	private DierRepository dierRepository;
	@Autowired
	private ReservatieRepository reservatieRepository;
	@Autowired
	private VerblijfplaatsRepository verblijfplaatsRepository;
	@Autowired
	private AddDierValidation addDierValidation;

	@GetMapping
	public String showHomePage(Model model, Principal principal) {
		System.out.println("Reservaties");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		boolean hasAdminRole = authentication.getAuthorities().stream()
		          .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
		model.addAttribute("isAdmin" ,hasAdminRole);
		if(hasAdminRole)
		{
			List<Reservatie> reservaties = (List<Reservatie>) reservatieRepository.findAll();
			model.addAttribute("reservaties", reservaties);
		}
		else
		{
			boolean hasUserRole = authentication.getAuthorities().stream()
			          .anyMatch(r -> r.getAuthority().equals("ROLE_USER"));
			if(hasUserRole)
			{
				List<Reservatie> reservaties = (List<Reservatie>) reservatieRepository.findByGereserveerdVoor(principal.getName());
				model.addAttribute("reservaties", reservaties);
			}
		}
		
		
		
		return "reservaties";
	}

	
}
