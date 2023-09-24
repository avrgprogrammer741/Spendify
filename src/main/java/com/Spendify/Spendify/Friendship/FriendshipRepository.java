package com.Spendify.Spendify.Friendship;

import com.Spendify.Spendify.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, Long> {

    Optional<Friendship> findByUserAndFriend(User user, User friend);

//    Optional<Friendship> findByUser(Long userId);
}
