package com.bioinformatics.spring.security.postgresql.repository;

import java.util.Optional;

import com.bioinformatics.spring.security.postgresql.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bioinformatics.spring.security.postgresql.models.ERole;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
