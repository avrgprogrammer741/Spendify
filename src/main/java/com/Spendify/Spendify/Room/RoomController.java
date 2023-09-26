package com.Spendify.Spendify.Room;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

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
    public ResponseEntity<Void> deleteRoom(@PathVariable("roomId") Long roomId) {
        try {
            roomService.deleteRoom(roomId);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public void addRoom() {
        roomService.createRoom();
    }

    @DeleteMapping("/{roomId}/users/{userId}")
    public ResponseEntity<Void> removeUserFromRoom(
            @PathVariable("roomId") Long roomId,
            @PathVariable("userId") Long userId) {
        try {
            roomService.removeUser(roomId, userId);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException | NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
