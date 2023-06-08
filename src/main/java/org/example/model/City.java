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
@Table(name = "City")
@AllArgsConstructor
@NoArgsConstructor
public class City extends BaseEntity {

    // Class representing the database model for city data

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "City", sequenceName = "City_ID_SEQ")
    private Long id;

    @Column(name = "NAME", length = 200, nullable = false)
    private String name;

    @Column(name = "USER_ID", nullable = false)
    private Long userId;

}
