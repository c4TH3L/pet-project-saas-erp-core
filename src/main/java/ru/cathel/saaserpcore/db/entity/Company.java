package ru.cathel.saaserpcore.db.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "companies")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "founder_id")
    private Founder founder;

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @Column(name = "company_code", nullable = false, length = 30)
    private String companyCode;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;
}
