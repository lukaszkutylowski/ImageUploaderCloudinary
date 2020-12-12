package lukaszkutylowski.collection;

import lukaszkutylowski.model.AppUser;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class AppUserCollection {

    private Map<Integer, AppUser> users = new LinkedHashMap<>();
    private Integer id = 0;

    public AppUser save(AppUser user) {
        id += 1;
        users.put(id, user);
        return users.get(id);
    }

    public Optional<AppUser> findByUsername(String s) {
        for (int i = 1; i <= users.size(); i++) {
            if (users.get(i).getUsername().equals(s)) {
                return Optional.of(users.get(i));
            }
        }
        return Optional.empty();
    }
}
