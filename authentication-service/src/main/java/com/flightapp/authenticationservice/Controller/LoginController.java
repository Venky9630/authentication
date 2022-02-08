package com.flightapp.authenticationservice.Controller;

import com.flightapp.authenticationservice.Utils.JwtUtility;
import com.flightapp.authenticationservice.models.AuthenticationDetails;
import com.flightapp.authenticationservice.models.JwtRequest;
import com.flightapp.authenticationservice.models.JwtResponse;
import com.flightapp.authenticationservice.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class LoginController {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private JwtUtility jwtUtility;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder encoder;

    @GetMapping("/")
    public String home(){
        return "Welcome to JWT Tutorial";
    }

    @PostMapping("/authenticate")
    public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception {
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getEmail(),
                            jwtRequest.getPwd()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Invalid Credentials", e);
        }

        final UserDetails userDetails
                = myUserDetailsService.loadUserByUsername(jwtRequest.getEmail());

        final String token =
                jwtUtility.generateToken(userDetails);

        return new JwtResponse(token);
    }

    @PostMapping("/register")
    public AuthenticationDetails register(@RequestBody AuthenticationDetails user) throws Exception {

        if(user.getEmail()!=null && "".equals(user.getEmail())){
            AuthenticationDetails userObj = myUserDetailsService.loadUserByEmail(user.getEmail());
            if(userObj!= null){
                throw new Exception("user with "+user.getEmail()+" is already exists");
            }
        }
        AuthenticationDetails userObject = null;
        // Create new user's account
        AuthenticationDetails userObjectForPass = new AuthenticationDetails(user.getUname(),
                user.getEmail(),
                user.getAge(),
                user.getGender(),
                user.getRoles(),
                encoder.encode(user.getPwd()));
        userObject = myUserDetailsService.saveUser(userObjectForPass);
        return userObject;
    }

    //    @PostMapping("/login")
//    @CrossOrigin
//    public User loginUser(@RequestBody User user) throws Exception {
//        String email = user.getEmail();
//        String password = user.getPwd();
//        User userObj = null;
//        if(email != null && password != null){
//          userObj=  myUserDetailsService.fetchUserByEmailAndPwd(email, password);
//        }
//        if(userObj == null){
//            throw new Exception("Bad credentials");
//        }
//        return userObj;
//    }

    @GetMapping("/user")
    public String user(){
        return ("<h1>Welcome User!!!</h1>");
    }

    @GetMapping("/admin")
    public String admin(){
        return ("<h1>Welcome Admin!!!</h1>");
    }
}
