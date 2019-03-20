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
    @JoinColumn(name = "to_user")
    private User messageToUser;

    @Column(name="from_user_id")
    private Long messageFrom;

    public Message()
    {}

    public Message(String message, Date date, User messageFromUser, User messageToUser) {
        this.message = message;
        this.date = date;
        this.messageToUser = messageToUser;
        this.messageFrom = messageFromUser.getId();
    }

}
