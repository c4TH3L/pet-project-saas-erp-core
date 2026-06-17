package ru.cathel.saaserpcore.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.cathel.saaserpcore.db.entity.Permission;

import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Integer> {
    @Query("SELECT r.permissions FROM Role r where r.id = :roleId")
    List<Permission> findAllByRoleId(@Param("roleId") int roleId);
}
