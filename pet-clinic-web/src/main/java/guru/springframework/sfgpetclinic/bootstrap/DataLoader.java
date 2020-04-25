package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final VetService vetService;
    private final OwnerService ownerService;

    public DataLoader(VetService vetService, OwnerService ownerService) {
        this.vetService = vetService;

        this.ownerService = ownerService;
    }

    @Override
    public void run(String... args) throws Exception {
        Owner owner1 = new Owner();
        owner1.setFirstName("Rahul");
        owner1.setLastName("Singh");
        owner1.setId(1L);

        Owner owner2 = new Owner();
        owner2.setFirstName("Pria");
        owner2.setLastName("Pundir");
        owner2.setId(2L);

        ownerService.save(owner1);
        ownerService.save(owner2);

        System.out.println("Loaded Owners into Map .....");

        Vet vet1 = new Vet();
        vet1.setFirstName("Gaurav");
        vet1.setLastName("Singh");
        vet1.setId(1L);

        Vet vet2 = new Vet();
        vet2.setFirstName("Surender");
        vet2.setLastName("Singh");
        vet2.setId(2L);

        vetService.save(vet1);
        vetService.save(vet2);

        System.out.println("Loaded Vets into Map .....");

    }
}
