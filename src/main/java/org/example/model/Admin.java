package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.general.BaseEntity;

@Entity
@Getter
@Setter
@Table(name = "Admin")
@AllArgsConstructor
@NoArgsConstructor
public class Admin extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "User", sequenceName = "USER_ID_SEQ")
    private Long id;

    @Column(name = "USER_NAME", length = 50, nullable = false, unique = true)
    private String username;

    @Column(name = "PASSWORD", length = 50, nullable = false, unique = true)
    private String password;

    @Column(name = "USER_ID", nullable = false)
    private Long userId;
}
