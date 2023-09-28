package com.Spendify.Spendify.Room;

import com.Spendify.Spendify.User.User;
import com.Spendify.Spendify.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {
    private final RoomRepository roomRepository;
    private final RoomDTOMapper roomDTOMapper;

    public RoomService(RoomRepository roomRepository,
                       RoomDTOMapper roomDTOMapper) {
        this.roomRepository = roomRepository;
        this.roomDTOMapper = roomDTOMapper;
    }

    public List<RoomDTO> getAllRooms() {
        return roomRepository.findAll()
                .stream()
                .map(roomDTOMapper)
                .collect(Collectors.toList());
    }

    public RoomDTO getRoom(Long roomId) {
        return roomRepository.findById(roomId)
                .map(roomDTOMapper)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "room with id [%s] not found".formatted(roomId)
                ));
    }

    public void deleteRoom(Long roomId) {
        roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "room with id [%s] not found".formatted(roomId)
                ));
        roomRepository.deleteById(roomId);
    }

    public void createRoom() {
        Room room = new Room();
//        TODO: add authenticated user to userList who created Room
        roomRepository.save(room);
    }

    public void removeUser(Long roomId, Long userId) {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new ResourceNotFoundException(
                "room with id [%s] not found".formatted(roomId)
        ));
        List<User> userList = room.getUserList();
        User userToRemove = userList
                .stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException(
                        "user with id [%s] not found".formatted(userId)
                ));

        userList.remove(userToRemove);
        roomRepository.save(room);
    }
}

