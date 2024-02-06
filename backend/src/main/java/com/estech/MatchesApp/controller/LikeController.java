package com.estech.MatchesApp.controller;

import com.estech.MatchesApp.dto.LikeDTO;
import com.estech.MatchesApp.model.Like;
import com.estech.MatchesApp.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/like")
@CrossOrigin
public class LikeController {

    @Autowired
    private LikeService likeService;

    @PostMapping
    public Map<String, String> like(@RequestBody LikeDTO likeDTO) {
        return likeService.saveLike(likeDTO.getUserOneId(), likeDTO.getUserTwoId());
    }

}
