package com.springboot.AsielBeheer;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
//import validator.ValidEmail;

@Getter @Setter
public class AddAnimal {

	//@Pattern(regexp = "^[a-zA-Z]+", message = "username must be alphanumeric with no spaces")

	//private ImageIcon foto;
 private String name;
 private String race;
 private String gender;
 private LocalDate birthday;
 private double medicalCosts;
 private boolean okWithYoungKids; 
 private boolean okWithOlderKids;
 private boolean okWithCats;
 private boolean okWithDogs;
 private boolean okAsIndoorCat;
 private boolean isReserved = false;
	//@NotBlank
    //@Size(min = 4, max = 20)
   

    // @NotBlank
   

    //@NotBlank
    //@Email
    //@ValidEmail
   

}