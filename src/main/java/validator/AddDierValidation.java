package validator;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.springboot.AsielBeheer.AddAnimal;

public class AddDierValidation implements Validator {

    @Override
    public boolean supports(Class<?> klass) {
        return AddAnimal.class.isAssignableFrom(klass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AddAnimal addAnimalRegistration = (AddAnimal) target;
        String gender = addAnimalRegistration.getGender();
        if (!(gender.equalsIgnoreCase("Mannelijk") ||gender.equalsIgnoreCase("Vrouwelijk"))) {
            errors.rejectValue("gender",
                    "inappropriateValue.addAnimalRegistration.gender",
                    "Gender must be \"Mannelijk\" or \"Vrouwelijk\"");
        }
        String identificationCode = addAnimalRegistration.getIdentificationCode();
        String firstPart = identificationCode.substring(0, 2);
        String middlePart = identificationCode.substring(3,7);
        String lastPart = identificationCode.substring(8);
        try
        {
        	int middleInt = Integer.parseInt(middlePart);
        	try
            {
            	int lastInt = Integer.parseInt(lastPart);
            	if(lastInt != middleInt%3)
            	{
            		errors.rejectValue("identificationCode",
                            "invalidFormat.addAnimalRegistration.identificationCode",
                            "identificationCode verification incorrect, middle 5 digits %3 != last 2 digits");
            	}
            }
            catch(Exception e)
            {
            	errors.rejectValue("identificationCode",
                        "invalidFormat.addAnimalRegistration.identificationCode",
                        "identificationCode format incorrect. last 2 elements of string are NAN");
            }
        }
        catch(Exception e)
        {
        	errors.rejectValue("identificationCode",
                    "invalidFormat.addAnimalRegistration.identificationCode",
                    "identificationCode format incorrect. middle 5 elements of string are NAN( elements 3-8)");
        }
    }
}
