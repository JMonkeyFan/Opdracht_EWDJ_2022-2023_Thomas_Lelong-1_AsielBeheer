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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import domain.Dier;
import domain.Reservatie;
import exception.CustomGenericException;
import repository.DierRepository;
import repository.ReservatieRepository;
import repository.VerblijfplaatsRepository;

@Controller
@RequestMapping("/dieren")
public class DierController {
	@ExceptionHandler(CustomGenericException.class)
	public ModelAndView handleCustomException(CustomGenericException ex)
	{
		ModelAndView model = new ModelAndView ("error/generic_error");
		model.addObject("errCode", ex.getErrCode());
		model.addObject("errMsg", ex.getErrMsg());
		return model;
	}
	@Autowired
	private DierRepository dierRepository;
	@Autowired
	private ReservatieRepository reservatieRepository;
	@Autowired
	private VerblijfplaatsRepository verblijfplaatsRepository;

	@GetMapping
	public String listDieren(Model model, Principal principal) {
		model.addAttribute("dierList", dierRepository.getAllSorted());
		model.addAttribute("principal", principal.getName());
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		boolean hasUserRole = authentication.getAuthorities().stream()
				.anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
		model.addAttribute("isAdmin", hasUserRole);
		List<Dier> sorted = dierRepository.getAllSorted();
		List<String> rassen = new ArrayList<>();
		for (int i = 0; i < sorted.size(); i++) {
			if (!rassen.contains(sorted.get(i).getRas())) {
				rassen.add(sorted.get(i).getRas());
			}
		}
		model.addAttribute("rassen", rassen);
		return "dieren";
	}

	@GetMapping(value = "/rassen/{ras}")
	public String listDierenFromRas(@PathVariable("ras") String dierras, Model model, Principal principal) {
		// model.addAttribute("dierList", dierRepository.getAllSorted());
		model.addAttribute("dierList", dierRepository.findByRas(dierras));
		model.addAttribute("geselecteerdRas", dierras);
		model.addAttribute("principal", principal.getName());
		return "dieren";
	}

	@GetMapping(value = "/reserveer/{id}")
	public String reserveer(@PathVariable("id") Integer dierId, Model model, Principal principal) {
		Dier dier = dierRepository.findById(dierId).get(0);
		if (dier == null) {
			return "redirect:/dieren";
		}
		if (!dier.isReedsGereserveerd()) {
			Reservatie reservatie = new Reservatie(dier, principal.getName());
			dier.setReedsGereserveerd(true);
			dierRepository.save(dier);
			reservatieRepository.save(reservatie);
		}
		model.addAttribute("dier", dier);
		return "redirect:/dieren/" + dierId;
	}

	@GetMapping(value = "/geefVrij/{id}")
	public String geefVrij(@PathVariable("id") Integer dierId, Model model, Principal principal) {
		Dier dier = dierRepository.findById(dierId).get(0);
		if (dier == null) {
			return "redirect:/dieren";
		}
		if (dier.isReedsGereserveerd()) {
			Reservatie reservatie = reservatieRepository.findByDier(dier).get(0);
			dier.setReedsGereserveerd(false);
			dierRepository.save(dier);
			reservatieRepository.delete(reservatie);
		}
		model.addAttribute("dier", dier);
		return "redirect:/dieren/" + dierId;
	}

	@GetMapping(value = "/{id}")
	public String show(@PathVariable("id") Integer dierId, Model model, Principal principal) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		boolean hasAdminRole = authentication.getAuthorities().stream()
				.anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
		boolean hasUserRole = authentication.getAuthorities().stream()
				.anyMatch(r -> r.getAuthority().equals("ROLE_USER"));
		/*
		 * 
		boolean hasAsielfanRole = authentication.getAuthorities().stream()
				.anyMatch(r -> r.getAuthority().equals("ROLE_ASIELFAN"));
		 */
		model.addAttribute("isAdmin", hasAdminRole);
		/*
		 * boolean canHaveMoreReservations = true;
		if(reservatieRepository.findByGereserveerdVoor(principal.getName()).size() >= 2)
		{
			if(!hasAdminRole)
			canHaveMoreReservations = false;
		}
		else if(reservatieRepository.findByGereserveerdVoor(principal.getName()).size() ==1)
		{
			if(!hasAsielfanRole && !hasAdminRole)
			canHaveMoreReservations = false;
		}
		
		model.addAttribute("canHaveMoreReservations", canHaveMoreReservations);
		 */
		boolean geefVrijVoorUser = false;
		boolean isGereserveerdDoorJou = false;
		if (dierRepository.findById(dierId).get(0) != null) {
			if (reservatieRepository.findByDier(dierRepository.findById(dierId).get(0)) != null) {
				try {
					if (reservatieRepository.findByDier(dierRepository.findById(dierId).get(0)).get(0) != null) {

						if (reservatieRepository.findByDier(dierRepository.findById(dierId).get(0)).get(0)
								.getGereserveerdVoor().equalsIgnoreCase(principal.getName())) {
							if (!hasAdminRole) {
								geefVrijVoorUser = true;
								model.addAttribute("geefVrijVoorUser", geefVrijVoorUser);
							}
						}
					}
				} catch (Exception e) {

				}
			} else {
				model.addAttribute("isGereserveerdDoorJou", geefVrijVoorUser);
			}
		} else {
			model.addAttribute("isGereserveerdDoorJou", geefVrijVoorUser);
		}
		Dier dier = dierRepository.findById(dierId).get(0);
		if (dier == null) {
			return "redirect:/dieren";
		}
		model.addAttribute("dier", dier);
		return "detailDier";
	}
	

}
