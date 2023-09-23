package com.Spendify.Spendify.Room;

import com.Spendify.Spendify.User.User;
import com.Spendify.Spendify.User.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

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
