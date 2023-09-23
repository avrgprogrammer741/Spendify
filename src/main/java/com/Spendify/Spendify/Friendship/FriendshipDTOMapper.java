package com.Spendify.Spendify.Friendship;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class FriendshipDTOMapper implements Function<Friendship,FriendshipDTO> {
    @Override
    public FriendshipDTO apply(Friendship friendship) {
        return new FriendshipDTO(
                friendship.getId(),
                friendship.getFriendshipDate(),
                friendship.getUser(),
                friendship.getFriend()
        );
    }
}
