package ru.stas.NauJava.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.stas.NauJava.Dao.UserRepository;
import ru.stas.NauJava.Entity.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MyInMemoryUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public MyInMemoryUserDetailService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User appUser;
        try {
            appUser = userRepository.findByUsername(username).getFirst();
        }
        catch (Exception e) {
            throw new UsernameNotFoundException("user not found");
        }
        org.springframework.security.core.userdetails.User user = new
                org.springframework.security.core.userdetails.User(appUser.getUsername(), appUser.getPassword(),
                mapRoles(appUser));
        return user;
    }

    private Collection<GrantedAuthority> mapRoles(User appUser)
    {
        List<GrantedAuthority> collectionRoles = new ArrayList<>();
        collectionRoles.add(new SimpleGrantedAuthority("ROLE_" + appUser.getRole()));
        return collectionRoles;
    }
}
