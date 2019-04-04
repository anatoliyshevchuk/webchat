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

    @Column(name = "DateCurrentUserLastCheck")
    Date lastOpenedByCurrentUser;

    @Column(name = "isActive")
    boolean isActive;

    @Transient
    int countNewMessages;

    public Conversation()
    {}

    public Conversation(User currentUser, User partnerUser, Date date, boolean Active) {
        this.currentUser = currentUser;
        this.partnerUser = partnerUser;
        this.lastOpenedByCurrentUser = date;
        this.isActive = Active;
    }

    public Conversation(User currentUser, User partnerUser) {
        this.currentUser = currentUser;
        this.partnerUser = partnerUser;
        this.lastOpenedByCurrentUser = new Date();
    }

}
