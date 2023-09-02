package com.springboot.AsielBeheer;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.Dier;
import domain.Reservatie;
import repository.DierRepository;
import repository.ReservatieRepository;
import repository.VerblijfplaatsRepository;

@Controller
@RequestMapping("/dieren")
public class DierController {
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
		model.addAttribute("isAdmin" ,hasUserRole);
		List<Dier> sorted =  dierRepository.getAllSorted();
		List<String> rassen = new ArrayList<>();
		for(int i = 0; i<sorted.size(); i++)
				{
			if(!rassen.contains(sorted.get(i).getRas()))
			{
				rassen.add(sorted.get(i).getRas());
			}
				}
		model.addAttribute("rassen", rassen);
		return "dieren";
	}
	@GetMapping(value = "/rassen/{ras}")
	public String listDierenFromRas(@PathVariable("ras") String dierras, Model model, Principal principal) {
		//model.addAttribute("dierList", dierRepository.getAllSorted());
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
       if(!dier.isReedsGereserveerd())
       {
    	   Reservatie reservatie = new Reservatie(dier, principal.getName());
    	   dier.setReedsGereserveerd(true);
           dierRepository.save(dier);
           reservatieRepository.save(reservatie);
       }
       model.addAttribute("dier", dier);
        return "detailDier";
    }
	
	
	@GetMapping(value = "/{id}")
    public String show(@PathVariable("id") Integer dierId, Model model) {
       
       Dier dier = dierRepository.findById(dierId).get(0);
        if (dier == null) {
			return "redirect:/dieren";
		}
        model.addAttribute("dier", dier);
        return "detailDier";
    }
	/*
	 * @GetMapping("/reservaties")
	public String showHomePage(Model model) {
		List<Reservatie> reservaties = (List<Reservatie>) reservatieRepository.findAll();
		model.addAttribute("reservaties", reservaties);
		return "reservaties";
	}
	 */
	/*
	 * @GetMapping(value = "/reserveer/{id}")
    public String reserveer(@PathVariable("id") Integer dierId, Model model) {
       System.out.println("Reserveer");
       Dier dier = dierRepository.findById(dierId).get(0);
       if (dier == null) {
			return "redirect:/dieren";
		}
       if(!dier.isReedsGereserveerd())
       {
    	   Reservatie reservatie = new Reservatie(dier, "bla");
    	   dier.setReedsGereserveerd(true);
           dierRepository.save(dier);
           reservatieRepository.save(reservatie);
       }
       model.addAttribute("dier", dier);
        return "detailDier";
    }
	 */
	
}
