package com.estech.MatchesApp.service;

import com.estech.MatchesApp.model.Like;
import com.estech.MatchesApp.model.User;
import com.estech.MatchesApp.repository.LikeRepository;
import com.estech.MatchesApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LikeRepository likeRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    
    public Map<String, String> login(User recibedUser) {
        List<User> userList = userRepository.findAll();
        Map<String, String> map = new HashMap<>();

        map.put("error", "Login failed");

        userList.forEach( user -> {
            if(user.getUsername().equals(recibedUser.getUsername()) && user.getPassword().equals(recibedUser.getPassword())) {
                map.clear();
                map.put("success", "Login Started");
                map.put("user_id", user.getId().toString());
            }
        });

        return map;
    }

    public Map<String, String> like(Long idUserOne, Long idUserTwo) {
        User userOne = userRepository.findById(idUserOne).orElse(null);
        User userTwo = userRepository.findById(idUserTwo).orElse(null);
        Map<String, String> map = new HashMap<>();

        if(userOne != null && userTwo != null) {

            map.put("success", "Success like to " + userTwo.getName());
        }
        return map;
    }

    public List<Long> getLikesByUserId(Long id) {
        User user = userRepository.findById(id).orElse(null);
        List<Long> userList = new ArrayList<>();
        user.getLikesToMe().forEach(like -> {
            userList.add(like.getUserTwoId().getId());
        });
        return userList;
    }

    public List<Long> getOtherLikesByUserId(Long id) {
        User user = userRepository.findById(id).orElse(null);
        List<Long> userList = new ArrayList<>();
        user.getLikesToOther().forEach(like -> {
            userList.add(like.getUserOneId().getId());
        });
        return userList;
    }
}
