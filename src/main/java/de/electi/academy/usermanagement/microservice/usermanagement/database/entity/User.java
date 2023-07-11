package de.electi.academy.usermanagement.microservice.usermanagement.database.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    UUID userId;

    String name;

    String email;

    Date birthDate;

    Date creationDate;

    Date modifiedDate;

    String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Flag> flags;

    boolean admin;
}
