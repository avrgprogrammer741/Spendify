package com.Spendify.Spendify.Room;

import com.Spendify.Spendify.User.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Room {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_sequence")
    @SequenceGenerator(name = "room_sequence", sequenceName = "room_sequence", allocationSize = 1, initialValue = 4)
    @Id
    private Long id;
    @ManyToMany()
    private List<User> userList;
    public Room() {
        this.userList = new ArrayList<>(); // Inicjalizuj listę userList w konstruktorze
    }
}
