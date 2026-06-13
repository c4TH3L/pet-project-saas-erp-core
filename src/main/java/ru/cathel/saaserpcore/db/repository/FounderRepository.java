package ru.cathel.saaserpcore.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.cathel.saaserpcore.db.entity.Founder;

@Repository
public interface FounderRepository extends JpaRepository<Founder, Integer> {
    boolean existsByEmail(String email);
}
