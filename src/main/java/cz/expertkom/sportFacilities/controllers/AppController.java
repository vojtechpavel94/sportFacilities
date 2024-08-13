package cz.expertkom.sportFacilities.controllers;

import cz.expertkom.sportFacilities.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {
    @Autowired
    private RoleRepository userRoles;
    @RequestMapping("/")
    public String welcome() {
        return "index";
    }

}
