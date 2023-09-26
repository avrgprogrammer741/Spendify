package com.Spendify.Spendify.Friendship;

import java.util.Date;

public record FriendshipAddRequest(Long userId,
                                   Date friendshipDate,
                                   Long friendId) {
}
