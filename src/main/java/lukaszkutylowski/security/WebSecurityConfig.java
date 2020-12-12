package lukaszkutylowski.security;

import lukaszkutylowski.collection.AppUserCollection;
import lukaszkutylowski.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;
    private final AppUserCollection appUserCollection;

    @Autowired
    public WebSecurityConfig(
            UserDetailsServiceImpl userDetailsService,
            AppUserCollection appUserCollection) {
        this.userDetailsService = userDetailsService;
        this.appUserCollection = appUserCollection;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/upload").hasRole("ADMIN")
                .antMatchers("/gallery").hasRole("USER")
                .antMatchers("/").permitAll()
                .antMatchers("/logout").permitAll()
                .and()
                .formLogin().permitAll()
                .and()
                .csrf().disable()
                .httpBasic()
                ;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void get() {
        AppUser appUser = new AppUser("UserJan", passwordEncoder()
                .encode("User123"), "ROLE_USER");
        AppUser appAdmin = new AppUser("AdminJan", passwordEncoder()
                .encode("Admin123"), "ROLE_ADMIN");
        appUserCollection.save(appUser);
        appUserCollection.save(appAdmin);
    }
}
