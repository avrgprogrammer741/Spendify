package com.Spendify.Spendify.Room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/room")
public class RoomController {
    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public List<RoomDTO> getRooms() {
        return roomService.getAllRooms();
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<RoomDTO> getRoom(@PathVariable("roomId") Long roomId) {
        try {
            RoomDTO roomDTO = roomService.getRoom(roomId);
            return ResponseEntity.ok(roomDTO);
        } catch (ResponseStatusException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{roomId}")
    public void deleteRoom(@PathVariable("roomId") Long roomId) {
        roomService.deleteRoom(roomId);
    }

    @PostMapping
    public void addRoom() {
        roomService.createRoom();
    }

    @DeleteMapping("/{roomId}/users/{userId}")
    public void removeUserFromRoom(
            @PathVariable("roomId") Long roomId,
            @PathVariable("userId") Long userId) {
        roomService.removeUser(roomId, userId);
    }
}
