package com.estech.MatchesApp.service;

import com.estech.MatchesApp.model.Like;
import com.estech.MatchesApp.model.User;
import com.estech.MatchesApp.repository.LikeRepository;
import com.estech.MatchesApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class LikeService {

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private UserRepository userRepository;

    public Map<String, String> saveLike(Long userOneId, Long userTwoId) {
        Map<String, String> map = new HashMap<>();



        User userOne = userRepository.findById(userOneId).orElse(null);
        User userTwo = userRepository.findById(userTwoId).orElse(null);

        if (findMatch(userOneId, userTwoId)) {
            map.put("warning", "the match already exist");
            return map;
        }


        Like like = new Like();
        like.setUserOneId(userOne);
        like.setUserTwoId(userTwo);
        likeRepository.save(like);
        map.put("success", "Like dado correctamente");

        if (findMatch(userTwoId, userOneId)) {
            map.put("match", "users have matched");
            return map;
        }

        return map;

    }

    public boolean findMatch(Long userTwoId, Long userOneId) {
        List<Like> userTwoLikes = new ArrayList<>();
        User userTwo = userRepository.findById(userTwoId).orElse(null);
        AtomicBoolean ret = new AtomicBoolean(false);

        if (userTwo == null) {
            return ret.get();
        }

        userTwoLikes = userTwo.getLikesToMe();
        userTwoLikes.forEach(like -> {
            if (like.getUserTwoId().getId() == userOneId) {
                ret.set(true);
            }
        });
        return ret.get();
    }

}
