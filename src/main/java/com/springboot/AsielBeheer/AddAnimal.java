package com.springboot.AsielBeheer;

import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddAnimal {
	@NotBlank
	@Pattern(regexp = "[a-zA-Z ]*", message = "username must be alphanumeric, spaces allowed")
	private String name;
	@NotBlank
	@Pattern(regexp = "^[\\w\\-\\s]+$", message = "username must be alphanumeric, spaces allowed")
	private String race;
	@NotBlank
	@Pattern(regexp = "^[a-zA-Z]+", message = "username must be alphanumeric with no spaces")
	private String gender;
	@Past
	@NotNull
	private LocalDate birthday;
	@DecimalMin("0")
	private double medicalCosts;

	private boolean okWithYoungKids;
	private boolean okWithOlderKids;
	private boolean okWithCats;
	private boolean okWithDogs;
	private boolean okAsIndoorCat;
	private boolean isReserved = false;

//private ImageIcon foto;

}