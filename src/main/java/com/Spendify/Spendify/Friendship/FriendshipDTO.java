package com.Spendify.Spendify.Friendship;

import java.util.Date;

public record FriendshipDTO (Long id,
                             Date friendshipDate,
                             Long userId,
                             Long friendId){}