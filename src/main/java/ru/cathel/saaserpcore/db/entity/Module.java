package ru.cathel.saaserpcore.db.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "modules")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;
}
