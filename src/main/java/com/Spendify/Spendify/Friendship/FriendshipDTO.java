package com.Spendify.Spendify.Friendship;

import com.Spendify.Spendify.User.User;
import java.util.Date;

public record FriendshipDTO (Long id,
                             Date friendshipDate,
                             Long userId,
                             Long friendId){}