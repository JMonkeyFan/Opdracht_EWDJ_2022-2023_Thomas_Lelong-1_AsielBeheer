package com.springboot.AsielBeheer;

import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddAnimal {
	@NotBlank
	@Pattern(regexp = "[a-zA-Z ]*", message = "name must be alphanumeric, spaces allowed")
	private String name;
	@Pattern(regexp = "[a-zA-Z ]*", message = "name must be alphanumeric, spaces allowed")
	//@Pattern(regexp = "^$[a-zA-Z ]*", message = "race must be alphanumeric, spaces allowed")
	private String race;
	@NotBlank
	@Pattern(regexp = "^[a-zA-Z]+", message = "gender must be alphanumeric with no spaces")
	private String gender;
	@Past
	@NotNull
	private LocalDate birthday;
	@DecimalMin("0")
	private double medicalCosts;
	@NotBlank
	@Size(min = 10, max = 10)
	private String identificationCode;
	private boolean okWithYoungKids;
	private boolean okWithOlderKids;
	private boolean okWithCats;
	private boolean okWithDogs;
	private boolean okAsIndoorCat;
	private boolean isReserved = false;
	@Min(value = 50, message = "Must be a number >= 50")
	@Max(value = 300, message = "Must be a number <= 300")
	private int hokCode1;
	@Min(value = 50, message = "Must be a number >= 50")
	@Max(value = 300, message = "Must be a number <= 300")
	private int hokCode2;
	@NotBlank
	@Pattern(regexp = "^[a-zA-Z]+", message = "hokNaam must be a-z or A-Z with no spaces") 
	private String hokNaam;

//private ImageIcon foto;

}