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
public class Message implements Comparable<Message> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long id;

    @Column
    private String message;

    @Column
    private Date date;

    @Column(name = "to_user")
    private int messageToUser;

    @Column(name="from_user")
    private int messageFrom;

    @Column
    private boolean isNew;

    public Message()
    {}

    public Message(String message, Date date, User messageFromUser, User messageToUser, boolean isNew) {
        this.message = message;
        this.date = date;
        this.messageToUser = messageToUser.getId();
        this.messageFrom = messageFromUser.getId();
        this.isNew = isNew;
    }


    @Override
    public int compareTo(Message o) {
        if(this.date.getTime()<o.date.getTime()) {
            return -1;
        }
        if (this.date.getTime()>o.date.getTime()) {
            return 1;
        }
        return 0;
    }
}
