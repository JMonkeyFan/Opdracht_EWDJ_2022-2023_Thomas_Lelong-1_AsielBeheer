package validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.springboot.AsielBeheer.AddAnimal;

import domain.Dier;
import repository.DierRepository;

public class AddDierValidation implements Validator {

	@Autowired
	private DierRepository dierRepository;

	@Override
	public boolean supports(Class<?> klass) {
		return AddAnimal.class.isAssignableFrom(klass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		AddAnimal addAnimalRegistration = (AddAnimal) target;
		String identificationCode = addAnimalRegistration.getIdentificationCode();
		if (alreadyExists(identificationCode)) {
			errors.rejectValue("identificationCode", "AlreadyExists.addAnimalRegistration.identificationCode",
					"Animal with identificationCode " + identificationCode + " already exists");
		}

		String gender = addAnimalRegistration.getGender();
		if (!(gender.equalsIgnoreCase("Mannelijk") || gender.equalsIgnoreCase("Vrouwelijk"))) {
			errors.rejectValue("gender", "inappropriateValue.addAnimalRegistration.gender",
					"Gender must be \"Mannelijk\" or \"Vrouwelijk\"");
		}
		int hokCode1 = addAnimalRegistration.getHokCode1();
		int hokCode2 = addAnimalRegistration.getHokCode2();
		if (hokCode2 < hokCode1 + 50) {
			errors.rejectValue("hokCode2", "inappropriateValue.addAnimalRegistration.hokCode2",
					"HokCode2 moet minstens 50 groter zijn dan hokCode1");
		}
		String firstPart = identificationCode.substring(0, 2);
		String middlePart = identificationCode.substring(3, 7);
		String lastPart = identificationCode.substring(8);
		try {
			int middleInt = Integer.parseInt(middlePart);
			try {
				int lastInt = Integer.parseInt(lastPart);
				if (lastInt != middleInt % 3) {
					errors.rejectValue("identificationCode", "invalidFormat.addAnimalRegistration.identificationCode",
							"identificationCode verification incorrect, middle 5 digits %3 != last 2 digits");
				}
			} catch (Exception e) {
				errors.rejectValue("identificationCode", "invalidFormat.addAnimalRegistration.identificationCode",
						"identificationCode format incorrect. last 2 elements of string are NAN");
			}
		} catch (Exception e) {
			errors.rejectValue("identificationCode", "invalidFormat.addAnimalRegistration.identificationCode",
					"identificationCode format incorrect. middle 5 elements of string are NAN( elements 3-8)");
		}
	}

	private boolean alreadyExists(String name) {
		List<Dier> dieren = dierRepository.getAllSorted();
		for (int i = 0; i < dieren.size(); i++) {
			if (dieren.get(i).getIdentificationCode().equals(name)) {
				return true;
			}
		}
		return false;

	}
}
