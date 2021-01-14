package com.itAcademy.ex14diceplayer.security;


import com.itAcademy.ex14diceplayer.model.Player;
import com.itAcademy.ex14diceplayer.repository.IPlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import static java.util.Collections.emptyList;


@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private IPlayerRepository playerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Player player = playerRepository.findByUsername(username);
        if(player == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(player.getUsername(), player.getPassword(), emptyList());
        // hardcore data to test: return new User("Rita", "Rita", new ArrayList<>());
    }
}

