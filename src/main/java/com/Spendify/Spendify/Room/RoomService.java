package com.Spendify.Spendify.Room;

import com.Spendify.Spendify.User.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
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
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Room not found with ID: " + roomId));
    }

    public void deleteRoom(Long roomId) {
        roomRepository.deleteById(roomId);
    }

    public void createRoom() {
        Room room = new Room();
//        TODO: add authenticated user to userList who created Room
        roomRepository.save(room);
    }

    public void removeUser(Long roomId, Long userId) {
        Optional<Room> optionalRoom = roomRepository.findById(roomId);

        if (optionalRoom.isPresent()) {
            Room room = optionalRoom.get();
            List<User> userList = room.getUserList();
            Optional<User> optionalUserToRemove = userList.stream()
                    .filter(user -> user.getId().equals(userId))
                    .findFirst();

            if (optionalUserToRemove.isPresent()) {
                User userToRemove = optionalUserToRemove.get();
                userList.remove(userToRemove);
                roomRepository.save(room);
            } else {
                throw new IllegalArgumentException("Użytkownik o podanym ID nie należy do pokoju.");
            }
        } else {
            throw new NoSuchElementException("Pokój o podanym ID nie istnieje.");
        }
    }
}

