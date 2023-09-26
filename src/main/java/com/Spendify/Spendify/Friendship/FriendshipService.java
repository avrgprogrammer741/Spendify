package com.Spendify.Spendify.Friendship;

import com.Spendify.Spendify.User.User;
import com.Spendify.Spendify.User.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FriendshipService {
    private final FriendshipRepository friendshipRepository;
    private final FriendshipDTOMapper friendshipDTOMapper;
    private final UserRepository userRepository;

    public List<FriendshipDTO> getAllFriendships() {
        return friendshipRepository.findAll()
                .stream()
                .map(friendshipDTOMapper)
                .collect(Collectors.toList());

    }
    public FriendshipService(FriendshipRepository friendshipRepository, FriendshipDTOMapper friendshipDTOMapper, UserRepository userRepository) {
        this.friendshipRepository = friendshipRepository;
        this.friendshipDTOMapper = friendshipDTOMapper;
        this.userRepository = userRepository;
    }

    public FriendshipDTO getFriendship(Long friendshipId) {
        return friendshipRepository
                .findById(friendshipId)
                .map(friendshipDTOMapper)
                .orElseThrow(() -> new IllegalStateException("Friendship not found with ID: " + friendshipId));
    }

    public void addFriendship(FriendshipAddRequest addRequest) {
        User user = userRepository.findById(addRequest.userId()) .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + addRequest.userId()));
        User friend=userRepository.findById(addRequest.friendId()) .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + addRequest.friendId()));
        Friendship friendship=new Friendship();
        friendship.setFriendshipDate(addRequest.friendshipDate());
        friendship.setUser(user);
        friendship.setFriend(friend);
        friendshipRepository.save(friendship);
    }
    public void deleteFriendship(Long friendshipId) {
        Friendship friendship = friendshipRepository.getReferenceById(friendshipId);
        friendshipRepository.delete(friendship);
    }
}
