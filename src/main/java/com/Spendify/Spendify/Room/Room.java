package com.Spendify.Spendify.Room;

import com.Spendify.Spendify.User.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Room {
    @Id
    private Long id;
    @OneToMany
    private List<User> userList;


}
