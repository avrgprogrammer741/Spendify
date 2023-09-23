package com.Spendify.Spendify.Friendship;

import com.Spendify.Spendify.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@NoArgsConstructor
@Data
@Entity
public class Friendship {
    @Id
    @SequenceGenerator(name = "friendship_sequence",
            sequenceName = "friendship_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "friendship_sequence"
    )
    @Column(name = "friendship_id",
            length = 50,
            nullable = false
    )
    private Long id;
    private Date friendshipDate;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "friend_id")
    private User friend;

    public Friendship(Long id, Date friendshipDate, User user, User friend) {
        this.id = id;
        this.friendshipDate = friendshipDate;
        this.user = user;
        this.friend = friend;
    }
}
