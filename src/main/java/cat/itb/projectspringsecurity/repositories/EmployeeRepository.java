package cat.itb.projectspringsecurity.repositories;

import cat.itb.projectspringsecurity.models.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query
    Iterable<Employee> findByExecutiveIsTrue();

    @Query
    Iterable<Employee> findByPhoneNumberIsNull();
    @Query
    boolean existsEmployeeByNameEqualsAndEmailEquals(String name, String phoneNumber);

}
