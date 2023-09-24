package com.Spendify.Spendify.Friendship;

import com.Spendify.Spendify.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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

//    @GetMapping("{userId}")
//    public Optional<Friendship> getFriendship(@PathVariable Long userId) throws Exception {
//        return friendshipService.getUserFriendship(userId);
//    }
    @PutMapping
    public void setFriendship(Date date, User user, User friend)
    {
        friendshipService.setUserFriendship(date, user, friend);
    }
    @DeleteMapping
    public void deleteFriendship(User user, User friend) throws Exception {
        friendshipService.deleteFriendship(user,friend);
    }
}



