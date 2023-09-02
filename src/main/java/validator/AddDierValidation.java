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
    }
}
