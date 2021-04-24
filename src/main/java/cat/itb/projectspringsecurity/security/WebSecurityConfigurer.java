package cat.itb.projectspringsecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {
    private final String ADMIN_ROLE = "ADMIN";
    private final String USER_ROLE = "USER";


    @Autowired
    private DetailsServiceCustom detailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(detailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/h2-console/**", "/login", "/register").permitAll()
                .antMatchers("/employees/list/**").hasAnyRole(ADMIN_ROLE, USER_ROLE)
                .antMatchers("/employees/new/**", "/employees/delete/**", "/employees/edit/{id}").hasAnyRole(ADMIN_ROLE)
                .antMatchers("/static").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login")
                .and()
                .logout().permitAll();

        /*http.csrf().disable(); //per h2-console
        http.headers().frameOptions().disable();  //per h2-console*/
    }

}
