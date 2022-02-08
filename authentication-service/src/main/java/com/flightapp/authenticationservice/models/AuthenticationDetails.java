package com.flightapp.authenticationservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "userstab")
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "uid", nullable = false)
    private Integer uid;
    @Column(name="uname")
    private String uname;
    @Column(name="age")
    private String age;
    @Column(name="email")
    private String email;
    @Column(name = "gender")
    private String gender;
    @Column(name="pwd")
    private String pwd;

    @ElementCollection
    @CollectionTable(joinColumns=@JoinColumn(name="user_uid"))
    @OrderColumn(name = "index_id")
    private List<String> roles;

    public AuthenticationDetails(String uname, String email, String age, String gender, List<String> roles, String encode) {
    this.uname=uname;
    this.age = age;
    this.email = email;
    this.gender = gender;
    this.roles = roles;
    this.pwd = encode;
    }
}
