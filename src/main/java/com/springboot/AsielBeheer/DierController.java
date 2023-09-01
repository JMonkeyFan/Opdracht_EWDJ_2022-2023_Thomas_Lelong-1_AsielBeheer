package com.springboot.AsielBeheer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import repository.DierRepository;

@Controller
@RequestMapping("/dieren")
public class DierController {
	@Autowired
	private DierRepository repository;
	@GetMapping
	public String listDieren(Model model) {
		model.addAttribute("dierList", repository.findAll());
		model.addAttribute("dier", repository.findByName("Jack"));
		return "dieren";
	}
}
