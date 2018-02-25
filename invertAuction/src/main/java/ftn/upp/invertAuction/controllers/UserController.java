package ftn.upp.invertAuction.controllers;

import ftn.upp.invertAuction.dto.LoginDTO;
import ftn.upp.invertAuction.model.User;
import ftn.upp.invertAuction.security.TokenUtils;
import ftn.upp.invertAuction.services.UserDetailsServiceImpl;
import ftn.upp.invertAuction.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenUtils tokenUtils;

    @RequestMapping(value = "/proba", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
    public ResponseEntity proba() {
        return new ResponseEntity("OK", HttpStatus.OK);
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity login(@RequestBody LoginDTO loginDTO) {
        try {
            System.out.println("*** Pocinje login na backendu");

            if(loginDTO.getUsername() == null || loginDTO.getPassword() == null){
                return new ResponseEntity<>("Username or password must be inserted!", HttpStatus.BAD_REQUEST);
            }
            // Perform the authentication
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(),
                    loginDTO.getPassword());
            System.out.println("TOKEN: " + token);
            Authentication authentication = authenticationManager.authenticate(token);
            System.out.println("posle autent");
            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println("posle sec context holdera");
            // Reload user details so we can generate token
            UserDetails details = userDetailsService.loadUserByUsername(loginDTO.getUsername());

            String final_token = tokenUtils.generateToken(details);
            System.out.println("TOKE FINAL: " + final_token);
            User u = userService.findByToken(final_token);
            System.out.println("USER:" + u.getUsername());
            return new ResponseEntity<>(final_token + " " + u.getUsername(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("Invalid login",
                    HttpStatus.NOT_FOUND);
        }
    }
}
