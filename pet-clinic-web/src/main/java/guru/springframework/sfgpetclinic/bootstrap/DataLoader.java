package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final VetService vetService;
    private final OwnerService ownerService;
    private final PetTypeService petTypeService;

    public DataLoader(VetService vetService, OwnerService ownerService, PetTypeService petTypeService) {
        this.vetService = vetService;

        this.ownerService = ownerService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        dog.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("Rahul");
        owner1.setLastName("Singh");
        owner1.setAddress("540 West Winspear");
        owner1.setCity("Buffalo");
        owner1.setTelephone("7165478899");

        Pet rahulsPet = new Pet();
        rahulsPet.setPetType(savedDogPetType);
        rahulsPet.setOwner(owner1);
        rahulsPet.setBirthDate(LocalDate.now());
        rahulsPet.setName("Rosco");

        owner1.getPets().add(rahulsPet);


        Owner owner2 = new Owner();
        owner2.setFirstName("Pria");
        owner2.setLastName("Pundir");
        owner2.setAddress("84 Minnesota");
        owner2.setCity("Amherst");
        owner2.setTelephone("7163828877");

        Pet priasPet = new Pet();
        priasPet.setPetType(savedCatPetType);
        priasPet.setOwner(owner2);
        priasPet.setBirthDate(LocalDate.now());
        priasPet.setName("Hunter");

        owner2.getPets().add(priasPet);

        ownerService.save(owner1);
        ownerService.save(owner2);

        System.out.println("Loaded Owners into Map .....");

        Vet vet1 = new Vet();
        vet1.setFirstName("Gaurav");
        vet1.setLastName("Singh");


        Vet vet2 = new Vet();
        vet2.setFirstName("Surender");
        vet2.setLastName("Singh");


        vetService.save(vet1);
        vetService.save(vet2);

        System.out.println("Loaded Vets into Map .....");

    }
}
