package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/owners")
public class OwnerController {
    private final OwnerService ownerService;

    OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping({"/find"})
    public String findOwners(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return "owner/findOwners";
    }

    @GetMapping
    public String processFindForm(Owner owner, BindingResult result, Model model) {
        // allow parameter less get request for /owners to return all records
        if (owner.getLastName() == null) {
            owner.setLastName(""); //empty String broadest possible string
        }

        //fetch all users by the username
        List<Owner> owners = ownerService.findAllByLastNameLike(owner.getLastName());

        if (owners.isEmpty()) {
            //noOwnersFound
            result.rejectValue("lastName", "notFound", "not found");
            return "owner/findOwners";
        } else if (owners.size() == 1) {
            //1 owner found
            owner = owners.iterator().next();
            return "redirect:/owners/" + owner.getId();
        } else {
            model.addAttribute("selections", owners);
            return "owner/ownersList";
        }
    }

    @GetMapping("/{id}")
    public ModelAndView showOwner(@PathVariable String id) {
        Owner owner = ownerService.findById(Long.valueOf(id));
        ModelAndView mav = new ModelAndView("owner/ownerDetails");
        mav.addObject(owner);
        return mav;
    }
}
