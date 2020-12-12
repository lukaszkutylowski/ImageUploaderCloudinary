package lukaszkutylowski.security;

import lukaszkutylowski.collection.AppUserCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AppUserCollection appUserCollection;

    @Autowired
    public UserDetailsServiceImpl(AppUserCollection appUserCollection) {
        this.appUserCollection = appUserCollection;
    }

    @Override
    public UserDetails loadUserByUsername(String s) {
        try {
            if (appUserCollection.findByUsername(s).isPresent()) {
                return appUserCollection.findByUsername(s).get();
            }
        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
