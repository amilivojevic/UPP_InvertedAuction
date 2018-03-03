package ftn.upp.invertAuction.services;

import ftn.upp.invertAuction.model.Company;
import ftn.upp.invertAuction.repositories.CompanyRepository;
import ftn.upp.invertAuction.repositories.UserRepository;
import ftn.upp.invertAuction.model.User;
import ftn.upp.invertAuction.security.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    CompanyRepository companyRepository;

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public User findByToken(String token){
        String un = tokenUtils.getUsernameFromToken(token);
        UserDetails details = userDetailsService.loadUserByUsername(un);
        User user = findByUsername(details.getUsername());

        return user;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public Company hasCompany(User client){
        List<Company> companies = companyRepository.findAll();
        for (Company c : companies){
            if (c.getAgent().getId() == client.getId()){
                //client is agent of company c
                return c;
            }
        }
        // clinet isnt agent of any company
        return null;
    }
}
