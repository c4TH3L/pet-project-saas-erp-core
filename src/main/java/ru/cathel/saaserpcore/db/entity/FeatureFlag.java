package ru.cathel.saaserpcore.db.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.userdetails.User;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "feature_flags", uniqueConstraints = {
        @UniqueConstraint(name = "uk_sub_company_module",
                columnNames = {
                        "sub_company_id", "module_id"
                })
})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FeatureFlag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_company_id")
    private SubCompany subCompany;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "module_id")
    private Module module;

    @Column(name = "is_enabled", nullable = false)
    private boolean isEnabled;
}
