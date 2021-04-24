package cat.itb.projectspringsecurity.controllers;


import cat.itb.projectspringsecurity.models.entities.User;
import cat.itb.projectspringsecurity.models.services.ServiceUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ControllerLogin {

    @Autowired
    private ServiceUsers serviceUsers;


    @GetMapping("/login")
    public String login(Model m) {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userForm", new User());
        return "register";
    }

    @PostMapping("/register")
    public String submitUser(@ModelAttribute("userForm") User u, Model model)  {
        User employee = serviceUsers.findById(u.getUserName());

        if (employee==null){
            serviceUsers.add(u);
            return "redirect:/login";

        }
        model.addAttribute("errorMessage", "Username is not available");

        return "register";

    }
}
