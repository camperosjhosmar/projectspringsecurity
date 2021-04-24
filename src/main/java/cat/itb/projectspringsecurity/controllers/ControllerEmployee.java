package cat.itb.projectspringsecurity.controllers;

import cat.itb.projectspringsecurity.models.entities.Employee;
import cat.itb.projectspringsecurity.models.services.ServiceEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class ControllerEmployee {
    @Autowired
    private ServiceEmployee service;


    @GetMapping("employees/list")
    public String list(Model m) {
        m.addAttribute("listEmployees", service.getEmployees());
        return "list";
    }

    @GetMapping("employees/new")
    public String addEmployee(Model m) {
        m.addAttribute("employeeForm", new Employee());
        return "add";
    }

    @GetMapping("employees/edit/{id}")
    public String editEmployee(@PathVariable("id") int id, Model m) throws Exception {

        m.addAttribute("employeeForm", service.findById(id));
        return "add";
    }

    @GetMapping("employees/delete/{id}")
    public String deleteEmployee(@PathVariable("id") int id) throws Exception {
        service.deleteById(id);
        return "redirect:/employees/list";

    }


    @PostMapping("employees/new/submit")
    public String afegirSubmit(@ModelAttribute("employeeForm") Employee employee, Model model) {
        service.add(employee);
        return "redirect:/employees/list";
    }


    @PostMapping("employees/edit/submit")
    public String updateSubmit(@ModelAttribute("empleatForm") Employee employee) throws Exception {
        service.update(employee);
        return "redirect:/employees/list";

    }

    @GetMapping("employees/list/sortByPhoneNumberExecutiveDesc")
    public String sortByPhoneNumberDesc(Model m) {
        m.addAttribute("listEmployees", service.sortByPhoneNumberAndExecutive(Sort.Direction.DESC));
        return "list";
    }

    @GetMapping("employees/list/sortByPhoneNumberExecutiveAsc")
    public String sortByPhoneNumberAsc(Model m) {
        m.addAttribute("listEmployees", service.sortByPhoneNumberAndExecutive(Sort.Direction.ASC));
        return "list";
    }

    @GetMapping("/employees/list/executiveEmployees")
    public String executiveView(Model m) {
        m.addAttribute("listEmployees", service.findByExecutiveIsTrue());
        return "list";
    }

    @GetMapping("/employees/list/employeesWithoutPhone")
    public String viewEmployeesWithoutPhone(Model m) {
        m.addAttribute("listEmployees", service.findByPhoneNumberIsNull());
        return "list";
    }

}
