package ftn.upp.invertAuction.services;

import ftn.upp.invertAuction.Repositories.UserRepository;
import ftn.upp.invertAuction.model.User;
import ftn.upp.invertAuction.security.TokenUtils;
import ftn.upp.invertAuction.security.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public User findByToken(String token){
        String un = tokenUtils.getUsernameFromToken(token);
        UserDetails details = userDetailsService.loadUserByUsername(un);

        System.out.println("username : " + details.getUsername());
        User user = findByUsername(details.getUsername());

        return user;
    }
}
