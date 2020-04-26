package guru.springframework.sfgpetclinic.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class IndexController {

    @GetMapping({"/", "/index.html", ""})
    public String index(){
        return "index";
    }

    @GetMapping({"/oups"})
    public String oupsHandler() {
        return "owner/notimplemented";
    }
}
