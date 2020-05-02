package guru.springframework.sfgpetclinic.formatter;

import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Controller;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;


@Controller
public class PetTypeFormatter implements Formatter<PetType> {

    private final PetTypeService petTypeService;

    public PetTypeFormatter(PetTypeService petTypeService) {
        this.petTypeService = petTypeService;
    }

    @Override
    public PetType parse(String text, Locale locale) throws ParseException {
        Collection<PetType> findPetTypes = petTypeService.findAll();

        for(PetType petType : findPetTypes){
            if(petType.getName().equals(text)){
                return petType;
            }
        }
        throw new ParseException("Type not found" + text,0);
    }

    @Override
    public String print(PetType petType, Locale locale) {
        return petType.getName();
    }
}
