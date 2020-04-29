package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;


@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

    OwnerSDJpaService ownerSDJpaService;

    @Mock
    OwnerRepository ownerRepository;
    @Mock
    PetRepository petRepository;
    @Mock
    PetTypeRepository petTypeRepository;

    Long ownerId = 1L;
    Owner rahul;

    @BeforeEach
    void setUp() {
        initMocks(this);

        ownerSDJpaService = new OwnerSDJpaService(ownerRepository,
                petRepository,
                petTypeRepository);

        rahul = Owner.builder().id(ownerId).lastName("Rahul").build();

    }

    @Test
    void findByLastName() {
        when(ownerRepository.findByLastName("Rahul")).thenReturn(rahul);

        Owner foundRahul = ownerSDJpaService.findByLastName("Rahul");

        assertEquals(rahul, foundRahul);
        assertEquals(ownerId, foundRahul.getId());
        verify(ownerRepository, times(1)).findByLastName("Rahul");
    }

    @Test
    void findAll() {
        Set<Owner> owners = new HashSet<>();

        Owner owner1 = Owner.builder().id(1L).firstName("Rahul").build();
        Owner owner2 = Owner.builder().id(2L).firstName("Gaurav").build();

        owners.add(owner1);
        owners.add(owner2);

        when(ownerRepository.findAll()).thenReturn(owners);

        Set<Owner> returned = ownerSDJpaService.findAll();

        assertNotNull(returned);
        assertEquals(2, returned.size());
        verify(ownerRepository, times(1)).findAll();

    }

    @Test
    void findById() {
        Set<Owner> owners = new HashSet<>();

        Owner owner1 = Owner.builder().id(1L).build();
        Owner owner2 = Owner.builder().id(2L).build();

        owners.add(owner1);
        owners.add(owner2);

        when(ownerRepository.findById(ownerId)).thenReturn(Optional.of(rahul));
        Owner owner = ownerSDJpaService.findById(ownerId);

        assertEquals(ownerId, owner.getId());
        verify(ownerRepository, times(1)).findById(any());

    }

    @Test
    void findByIdNull() {
        when(ownerRepository.findById(ownerId)).thenReturn(Optional.empty());
        Owner owner = ownerSDJpaService.findById(ownerId);

        assertNull(owner);
    }

    @Test
    void save() {
        Owner toSave = Owner.builder().id(3L).build();

        when(ownerRepository.save(any())).thenReturn(toSave);

        Owner saved = ownerSDJpaService.save(toSave);

        assertEquals(toSave.getId(), saved.getId());
        verify(ownerRepository, times(1)).save(any());
    }

    @Test
    void delete() {
        ownerSDJpaService.delete(rahul);
        verify(ownerRepository, times(1)).delete(any());
    }

    @Test
    void deleteByID() {
        ownerSDJpaService.deleteByID(ownerId);
        verify(ownerRepository, times(1)).deleteById(anyLong());
    }

}
