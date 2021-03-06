package com.WebChat.Entity;

import lombok.*;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;


@Getter
@Setter
@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private String password;

    @Column
    private String email;

    public User()
    {}

    @Override
    public String toString() {
        return "User{ " +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
