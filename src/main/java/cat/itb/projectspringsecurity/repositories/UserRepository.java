package cat.itb.projectspringsecurity.repositories;

import cat.itb.projectspringsecurity.models.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {

}
