package com.Spendify.Spendify.Friendship;

import com.Spendify.Spendify.Debt.DebtAddRequest;
import com.Spendify.Spendify.Debt.DebtDTO;
import com.Spendify.Spendify.Debt.DebtUpdateRequest;
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

    @GetMapping("{friendshipId}")
    public FriendshipDTO getFriendship(@PathVariable ("friendshipId") Long friendshipId)
    {
        return friendshipService.getFriendship(friendshipId);
    }
    @DeleteMapping("/{friendshipId}")
    public void deleteFriendship(@PathVariable("friendshipId") Long friendshipId)
    {
        friendshipService.deleteFriendship(friendshipId);
    }
    @PostMapping
    public void addFriendship(@RequestBody FriendshipAddRequest addRequest) {
        friendshipService.addFriendship(addRequest);
    }
}



