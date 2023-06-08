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
@Table(name = "API_KEYS")
@AllArgsConstructor
@NoArgsConstructor
public class ApiKeyEntity extends BaseEntity{

    // Class representing the database model for API keys

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "API_KEY")
    private String apiKey;
}
