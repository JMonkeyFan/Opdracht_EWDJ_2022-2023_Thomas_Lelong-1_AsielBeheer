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
@RequestMapping("/dieren")
public class DierController {
	@Autowired
	private DierRepository dierRepository;
	@Autowired
	private ReservatieRepository reservatieRepository;
	@Autowired
	private VerblijfplaatsRepository verblijfplaatsRepository;
	
	@GetMapping
	public String listDieren(Model model) {
		model.addAttribute("dierList", dierRepository.getAllSorted());
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
	public String listDierenFromRas(@PathVariable("ras") String dierras, Model model) {
		//model.addAttribute("dierList", dierRepository.getAllSorted());
		model.addAttribute("dierList", dierRepository.findByRas(dierras));
		model.addAttribute("geselecteerdRas", dierras);
		return "dieren";
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
	@GetMapping(value = "/reserveer/{id}")
    public String reserveer(@PathVariable("id") Integer dierId, Model model) {
       
       Dier dier = dierRepository.findById(dierId).get(0);
       Reservatie reservatie = new Reservatie(dier, "Jeff");
       dier.setReedsGereserveerd(true);
       dierRepository.save(dier);
       reservatieRepository.save(reservatie);
       
        if (dier == null) {
			return "redirect:/dieren";
		}
        model.addAttribute("dier", dier);
        return "detailDier";
    }
}
