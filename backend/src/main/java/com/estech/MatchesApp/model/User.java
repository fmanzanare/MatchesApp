package com.estech.MatchesApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String username;
    private String email;
    private String password;
    private Integer age;
    private String phone;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Temporal(TemporalType.DATE)
    private Date registerDate;
    @ManyToMany
    @JoinTable(
            name = "matches",
            joinColumns = @JoinColumn(name = "userOneId"),
            inverseJoinColumns = @JoinColumn(name = "userTwoId")
    )
    private Set<User> myLikes = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "matches",
            joinColumns = @JoinColumn(name = "userTwoId"),
            inverseJoinColumns = @JoinColumn(name = "userOneId")
    )
    private Set<User> otherLikesToMe = new HashSet<>();

    public void addToMyLikes(User user) {
        myLikes.add(user);
    }

    public void addToOtherLikes(User user) {
        otherLikesToMe.add(user);
    }
}
