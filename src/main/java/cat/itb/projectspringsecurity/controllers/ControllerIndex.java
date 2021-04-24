package cat.itb.projectspringsecurity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControllerIndex {

    @RequestMapping(value = "/")
    public String rootToIndex() {
        return "index";
    }

}
