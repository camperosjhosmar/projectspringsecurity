package cat.itb.projectspringsecurity.models.services;

import cat.itb.projectspringsecurity.models.entities.User;
import cat.itb.projectspringsecurity.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class ServiceUsers {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    public void add(User u) {
        u.setRol(u.getRol());
        u.setPassword(passwordEncoder().encode(u.getPassword()));
        userRepository.save(u);
    }


    @PostConstruct
    public void init() {

        userRepository.save(new User("user", passwordEncoder().encode("$9pg%KKS@Q9Z``+{3/}:#N[^Xn':}y[@jJDff"), "USER"));
        userRepository.save(new User("admin", passwordEncoder().encode("SBQrzDj59*j'U%Hmr9d%9]BK5zVWb36Xg?g~5Z<r"), "ADMIN"));
    }

    public User findById(final String userName) {
        return userRepository.findById(userName).orElse(null);
    }



}
