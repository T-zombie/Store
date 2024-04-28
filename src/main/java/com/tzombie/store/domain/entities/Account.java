package com.tzombie.store.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Entity
@Getter
@Setter
@Table(name = "account")
public class Account {

    @Id
    private UUID id;

    @Column(name = "username", nullable = false, updatable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    public Account() {
        var random = ThreadLocalRandom.current();
        this.id = new UUID(random.nextLong(), random.nextLong());
    }

    public Account(UUID id, String username, String password) {
        if (Objects.isNull(id)) {
            var random = ThreadLocalRandom.current();
            id = new UUID(random.nextLong(), random.nextLong());
        }
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
