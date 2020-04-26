package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/owners")
public class OwnerController {
    private final OwnerService ownerService;

    OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping({"/", "/index.html", ""})
    public String ownerList(Model model) {
        model.addAttribute("owners", ownerService.findAll());
        return "owner/owner-index";
    }

    @GetMapping({"/find"})
    public String errorHandler() {
        return "owner/notimplemented";
    }
}
