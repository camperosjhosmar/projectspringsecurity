package cat.itb.projectspringsecurity.models.services;

import cat.itb.projectspringsecurity.models.entities.Address;
import cat.itb.projectspringsecurity.models.entities.Employee;
import cat.itb.projectspringsecurity.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ServiceEmployee {

    @Autowired
    private EmployeeRepository employeeRepository;


    public void add(Employee e) {

        e.addAddress(e.getAddress());
        String phoneNumber=e.getPhoneNumber();

        e.setPhoneNumber(( phoneNumber== null||phoneNumber.length()==0) ? null : phoneNumber);

        employeeRepository.save(e);



    }

    public Iterable<Employee> getEmployees() {
        return employeeRepository.findAll();

    }

    @PostConstruct
    public void init() {

        employeeRepository.saveAll(Arrays.asList(
                new Employee("name1", "email1@gmail.com", "12345465", true, new Address("addres1", "Barcelona", "0000")),
                new Employee("name2", "email2@gmail.com", "874236", false, new Address("addres2", "Madrid", "0002")),
                new Employee("name3", "email3@gmail.com", "216746", false, new Address("addres3", "Barcelona", "0003")),
                new Employee("name4", "email4@gmail.com", "1623457", true, new Address("addres4", "Madrid", "0004")),
                new Employee("name5", "email5@gmail.com", null, false, new Address("addres5", "Barcelona", "0005"))

        ));

    }

    public Employee findById(int id) throws Exception {
        return employeeRepository.findById(id).orElseThrow(() -> new Exception("El empleado no existe" + id));

    }

    public void update(Employee employee) throws Exception {

        Employee e = findById(employee.getId());

        e.setName(employee.getName());
        e.setEmail(employee.getEmail());
        e.setPhoneNumber(employee.getPhoneNumber());

        String phoneNumber=employee.getPhoneNumber();

        e.setPhoneNumber((phoneNumber == null||phoneNumber.length()==0) ? null : phoneNumber);

        e.setExecutive(employee.isExecutive());
        e.setAddress(employee.getAddress());

        employeeRepository.save(e);


    }

    public void deleteById(int id) throws Exception {
        Employee employee = findById(id);
        employee.removeAddress();
        employeeRepository.delete(employee);
    }

    public Iterable<Employee> sortByPhoneNumberAndExecutive(Sort.Direction direction) {

        List<Order> orders = new ArrayList<>();
        Order order1 = new Order(direction, "phoneNumber");
        orders.add(order1);

        Order order2 = new Order(direction, "executive");
        orders.add(order2);

        return employeeRepository.findAll(Sort.by(orders));
    }

    public Iterable<Employee> findByExecutiveIsTrue() {

        return employeeRepository.findByExecutiveIsTrue();
    }

    public Iterable<Employee> findByPhoneNumberIsNull() {

        return employeeRepository.findByPhoneNumberIsNull();
    }

}
