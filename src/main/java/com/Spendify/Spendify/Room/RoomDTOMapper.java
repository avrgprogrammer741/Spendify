package com.Spendify.Spendify.Room;

import com.Spendify.Spendify.User.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
public class RoomDTOMapper implements Function<Room, RoomDTO> {
    @Override
    public RoomDTO apply(Room room) {
        List<Long> userIds = room.getUserList().stream()
                .map(User::getId)
                .toList();
        return new RoomDTO(
                room.getId(),
                userIds
        );
    }
}
