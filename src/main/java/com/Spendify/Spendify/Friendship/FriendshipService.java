package com.Spendify.Spendify.Friendship;

import com.Spendify.Spendify.User.User;
import com.Spendify.Spendify.User.UserRepository;
import com.Spendify.Spendify.exception.DuplicateResourceException;
import com.Spendify.Spendify.exception.FieldRequiredException;
import com.Spendify.Spendify.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
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
                .orElseThrow(() -> new ResourceNotFoundException("Friendship with ID [%s] not found".formatted(friendshipId)));
    }

    public void addFriendship(FriendshipAddRequest addRequest) {
        if(addRequest.friendId()==null)
        {
            throw new FieldRequiredException("Missing data ID friend");
        }
        if (addRequest.userId()==null)
        {
            throw new FieldRequiredException("Missing data ID user");
        }
        User user = userRepository.findById(addRequest.userId()) .orElseThrow(() -> new ResourceNotFoundException("User with ID [%s] not found".formatted(addRequest.userId())));
        User friend=userRepository.findById(addRequest.friendId()) .orElseThrow(() -> new ResourceNotFoundException("User with ID [%s] not found".formatted(addRequest.friendId())));

        if (Objects.equals(user.getId(), friend.getId()))
        {
            throw new DuplicateResourceException("Friendship with yourself is impossible");
        }
        boolean friendshipExists = friendshipRepository.existsByUserAndFriend(user, friend) ||
                friendshipRepository.existsByUserAndFriend(friend, user);

        if (friendshipExists) {
            throw new DuplicateResourceException("Friendship already exists");
        }
        Friendship friendship=new Friendship();
        friendship.setFriendshipDate(new Date());
        friendship.setUser(user);
        friendship.setFriend(friend);
        friendshipRepository.save(friendship);
    }
    public void deleteFriendship(Long friendshipId) {
        Friendship friendship = friendshipRepository.findById(friendshipId).orElseThrow(() -> new ResourceNotFoundException("Friendship with ID [%s] not found".formatted(friendshipId)));
        friendshipRepository.delete(friendship);
    }
}