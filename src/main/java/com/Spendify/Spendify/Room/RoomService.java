package com.Spendify.Spendify.Room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {
    private final RoomRepository roomRepository;
    private final RoomDTOMapper roomDTOMapper;

    public RoomService(RoomRepository roomRepository, RoomDTOMapper roomDTOMapper) {
        this.roomRepository = roomRepository;
        this.roomDTOMapper = roomDTOMapper;
    }

    public List<RoomDTO> getAllRooms() {
        return roomRepository.findAll()
                .stream()
                .map(roomDTOMapper)
                .collect(Collectors.toList());
    }
}

