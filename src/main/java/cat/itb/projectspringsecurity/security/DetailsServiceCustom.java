package cat.itb.projectspringsecurity.security;

import cat.itb.projectspringsecurity.models.entities.User;
import cat.itb.projectspringsecurity.models.services.ServiceUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DetailsServiceCustom implements UserDetailsService {
    @Autowired
    private ServiceUsers serviceUsers;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User u = serviceUsers.findById(s);
        UserBuilder builder = null;
        if (u != null) {
            builder = org.springframework.security.core.userdetails.User.withUsername(s);
            builder.disabled(false);
            builder.password(u.getPassword());
            builder.roles(u.getRol());
        } else throw new UsernameNotFoundException("User not found!");
        return builder.build();

    }
}
