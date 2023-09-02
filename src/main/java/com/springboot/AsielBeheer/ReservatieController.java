package com.springboot.AsielBeheer;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import domain.Reservatie;
import exception.CustomGenericException;
import repository.DierRepository;
import repository.ReservatieRepository;
import repository.VerblijfplaatsRepository;
import validator.AddDierValidation;

@Controller
@RequestMapping("/reservaties")
public class ReservatieController {
	@ExceptionHandler(CustomGenericException.class)
	public ModelAndView handleCustomException(CustomGenericException ex)
	{
		ModelAndView model = new ModelAndView ("error/generic_error");
		model.addObject("errCode", ex.getErrCode());
		model.addObject("errMsg", ex.getErrMsg());
		return model;
	}
	@Autowired
	private ReservatieRepository reservatieRepository;
	@Autowired
	private ReservatieRepository dierRepository;

	@GetMapping
	public String showHomePage(Model model, Principal principal) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		boolean hasAdminRole = authentication.getAuthorities().stream()
				.anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
		model.addAttribute("isAdmin", hasAdminRole);
		if (hasAdminRole) {
			List<Reservatie> reservaties = (List<Reservatie>) reservatieRepository.findAll();
			model.addAttribute("reservaties", reservaties);
			
		} else {
			boolean hasUserRole = authentication.getAuthorities().stream()
					.anyMatch(r -> r.getAuthority().equals("ROLE_USER"));
			if (hasUserRole) {
				List<Reservatie> reservaties = (List<Reservatie>) reservatieRepository
						.findByGereserveerdVoor(principal.getName());
				model.addAttribute("reservaties", reservaties);
			}
		}
		return "reservaties";
	}
}
/*
			List<Long> indexes = new ArrayList<>();
			for(int i = 0; i< reservaties.size(); i++)
			{	
				indexes.add(reservaties.get(i).getId());
			}
			model.addAllAttributes("indexes", indexes);*/