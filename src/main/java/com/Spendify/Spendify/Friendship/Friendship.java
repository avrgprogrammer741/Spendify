package com.Spendify.Spendify.Friendship;

import com.Spendify.Spendify.User.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@NoArgsConstructor
@Data
@Entity
@Table(name = "friendships")
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
    @Column(name = "id",
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

    public Friendship(Date friendshipDate, User user, User friend) {
        this.friendshipDate = friendshipDate;
        this.user = user;
        this.friend = friend;
    }
}
