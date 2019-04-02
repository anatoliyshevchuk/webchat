package com.WebChat.Entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Data
@Entity
@Table(name="conversation")
public class Conversation  {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "CurrentUser")
    User currentUser;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "PartnerUser")
    User partnerUser;

    @Column(name = "Date")
    Date lastOpenedByCurrentUser;

    @Transient
    int countNewMessages;

    public Conversation()
    {}

    public Conversation(User currentUser, User partnerUser, Date date) {
        this.currentUser = currentUser;
        this.partnerUser = partnerUser;
        this.lastOpenedByCurrentUser = date;
    }

    public Conversation(User currentUser, User partnerUser) {
        this.currentUser = currentUser;
        this.partnerUser = partnerUser;
        this.lastOpenedByCurrentUser = new Date();
    }

}
