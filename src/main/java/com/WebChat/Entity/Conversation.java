package com.WebChat.Entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

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

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "CurrentUser")
    User currentUser;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "PartnerUser")
    User partnerUser;

    @Transient
    List<Message> listMessages;

    public Conversation()
    {}

    public Conversation(User currentUser, User partnerUser) {
        this.currentUser = currentUser;
        this.partnerUser = partnerUser;
    }

}
