package ru.cathel.saaserpcore.db.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "sub_companies")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SubCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @Column(name = "address", length = 500)
    private String address;

    @Column(name = "description")
    private String description;
}
