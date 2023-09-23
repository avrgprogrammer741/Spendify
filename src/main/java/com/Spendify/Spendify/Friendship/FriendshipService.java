package com.Spendify.Spendify.Friendship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FriendshipService {
    private final FriendshipRepository friendshipRepository;
    private final FriendshipDTOMapper friendshipDTOMapper;
    @Autowired
    public List<FriendshipDTO> getAllFriendships() {
        return friendshipRepository.findAll()
                .stream()
                .map(friendshipDTOMapper)
                .collect(Collectors.toList());

    }
    public FriendshipService(FriendshipRepository friendshipRepository,FriendshipDTOMapper friendshipDTOMapper) {
        this.friendshipRepository = friendshipRepository;
        this.friendshipDTOMapper = friendshipDTOMapper;
    }
}
