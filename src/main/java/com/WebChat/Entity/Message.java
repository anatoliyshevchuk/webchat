package com.WebChat.Entity;


import lombok.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@Data
@Entity
@Table(name="Message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long id;

    @Column
    private String message;

    @Column
    private Date date;

    @ManyToOne
    @JoinColumn(name = "from_user_id")
    private User fromUser;

    public Message()
    {}

    @Override
    public String toString() {
        return message + date +
                ", from User=" + fromUser;
    }
}
