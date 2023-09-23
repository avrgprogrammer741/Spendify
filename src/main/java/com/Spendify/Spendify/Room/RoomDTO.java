package com.Spendify.Spendify.Room;

import com.Spendify.Spendify.User.User;

import java.util.List;

public record RoomDTO(Long id, List<Long> userIds) {
}
