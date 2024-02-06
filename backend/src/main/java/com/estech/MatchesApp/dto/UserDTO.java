package com.estech.MatchesApp.dto;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class UserDTO {
    private Long id;
    private String name;
    private String username;
    private String email;
    private String password;
    private Integer age;
    private String phone;
    private Date registerDate;
    private Set<Long> matches = new HashSet<>();

}
