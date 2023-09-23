package com.Spendify.Spendify.Friendship;

import com.Spendify.Spendify.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Data
@Entity
public class Friendship {
    @Id
    private Long id;
    private Date friendshipDate;

    @JsonIgnore
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
