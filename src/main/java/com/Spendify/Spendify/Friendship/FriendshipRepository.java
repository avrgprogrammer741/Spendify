package com.Spendify.Spendify.Friendship;

import com.Spendify.Spendify.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
    boolean existsByUserAndFriend(User user, User friend);
}
