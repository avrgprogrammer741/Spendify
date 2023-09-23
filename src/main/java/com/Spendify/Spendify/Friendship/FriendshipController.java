package com.Spendify.Spendify.Friendship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping(path = "api/v1/friendships/")
public class FriendshipController {
    private final FriendshipService friendshipService;
    @Autowired
    public FriendshipController(FriendshipService friendshipService) {
        this.friendshipService = friendshipService;
    }

    @GetMapping()
    public List<FriendshipDTO> getFriendships(){
        return friendshipService.getAllFriendships();
    }

}



