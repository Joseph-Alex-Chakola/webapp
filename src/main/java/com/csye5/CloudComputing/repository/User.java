package com.csye5.CloudComputing.repository;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.ReadOnlyProperty;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "person")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Size(min = 2, max = 50)
    private String firstName;
    @Size(min = 1, max = 50)
    private String lastName;
    @Email
    private String username;

    private String password;
    @CreationTimestamp
    private Timestamp accountCreated;
    @UpdateTimestamp
    private Timestamp accountUpdated;
    @Column
    private boolean verified=false;
    private String verificationCode;
}
