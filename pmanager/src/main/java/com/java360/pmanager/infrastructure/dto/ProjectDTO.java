package com.java360.pmanager.infrastructure.dto;

import com.java360.pmanager.domain.entity.Project;
import com.java360.pmanager.domain.model.ProjectStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProjectDTO {
    private final String id;
    private final String name;
    private final String description;
    private final LocalDate initialDate;
    private final LocalDate finalDate;
    private final ProjectStatus status;

    public static ProjectDTO create(Project project) {
        return new ProjectDTO(
                project.getId(),
                project.getName(),
                project.getDescription(),
                project.getInitialDate(),
                project.getFinalDate(),
                project.getStatus()
        );
    }
}
