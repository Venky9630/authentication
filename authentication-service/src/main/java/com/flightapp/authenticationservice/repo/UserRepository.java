package com.flightapp.authenticationservice.repo;

import com.flightapp.authenticationservice.models.AuthenticationDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AuthenticationDetails, Long> {
    public AuthenticationDetails findByEmail(String email);

    public AuthenticationDetails findByEmailAndPwd(String email, String password);

    public AuthenticationDetails findByUname(String username);

//    Optional<User> findByUserName(String userName);
}
