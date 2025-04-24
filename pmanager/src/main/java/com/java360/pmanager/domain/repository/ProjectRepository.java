package com.java360.pmanager.domain.repository;

import com.java360.pmanager.domain.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, String> {

    Optional<Project> findByName(String name);

}
