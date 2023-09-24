package com.Spendify.Spendify.Friendship;

import com.Spendify.Spendify.User.User;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FriendshipService {
    private final FriendshipRepository friendshipRepository;
    private final FriendshipDTOMapper friendshipDTOMapper;

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

    public Optional<Friendship> getUserFriendship(Long userId) throws Exception {
        Optional<Friendship> friendship = friendshipRepository.findByUser(userId);
        if (friendship.isPresent()) {
            Friendship foundFriendship = friendship.get();
            return Optional.of(foundFriendship);
        } else {
            throw new Exception("Error");
        }
    }
    public void setUserFriendship(Date date, User user, User friend) {
        Friendship friendship=new Friendship(date,user,friend);
        friendshipRepository.save(friendship);
    }

    public void deleteFriendship(User user, User friend) throws Exception {
    Optional<Friendship> friendship = friendshipRepository.findByUserAndFriend(user, friend);
        if (friendship.isPresent()) {
        Friendship foundFriendship = friendship.get();
        friendshipRepository.delete(foundFriendship);
    } else {
            throw new Exception("Error");
    }
}
}
