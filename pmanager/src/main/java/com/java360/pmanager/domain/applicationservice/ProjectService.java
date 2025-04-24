package com.java360.pmanager.domain.applicationservice;

import com.java360.pmanager.domain.entity.Project;
import com.java360.pmanager.domain.exception.DuplicateProjectException;
import com.java360.pmanager.domain.exception.InvalidProjectStatusException;
import com.java360.pmanager.domain.exception.ProjectNotFoundException;
import com.java360.pmanager.domain.model.ProjectStatus;
import com.java360.pmanager.domain.repository.ProjectRepository;
import com.java360.pmanager.infrastructure.dto.SaveProjectDataDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Transactional
    public Project createProject(SaveProjectDataDTO saveProjectData) {
        if(existsProjectWithName(saveProjectData.getName(), null)) {
            throw new DuplicateProjectException(saveProjectData.getName());
        }
        Project project = Project
                .builder()
                .name(saveProjectData.getName())
                .description(saveProjectData.getDescription())
                .initialDate(saveProjectData.getInitialDate())
                .finalDate(saveProjectData.getFinalDate())
                .status(ProjectStatus.PENDING)
                .build();

        projectRepository.save(project);

        log.info("Project created: {}", project);
        return project;

    }

    public Project loadProject(String projectId) {
        return projectRepository
                .findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException(projectId));
    }

    @Transactional
    public void deleteProject(String projectId) {
        Project project = loadProject(projectId);
        projectRepository.delete(project);
    }

    @Transactional
    public Project updateProject(String projectId, SaveProjectDataDTO saveProjectData) {
        if(existsProjectWithName(saveProjectData.getName(), projectId)) {
            throw new DuplicateProjectException(saveProjectData.getName());
        }
        Project project = loadProject(projectId);

        project.setName(saveProjectData.getName());
        project.setDescription(saveProjectData.getDescription());
        project.setInitialDate(saveProjectData.getInitialDate());
        project.setFinalDate(saveProjectData.getFinalDate());
        project.setStatus(convertToProjectStatus(saveProjectData.getStatus()));

        return project;
    }

    private ProjectStatus convertToProjectStatus(String statusStr) {
        try {
            return ProjectStatus.valueOf(statusStr);
        }catch (IllegalArgumentException | NullPointerException e) {
            throw new InvalidProjectStatusException(statusStr);
        }
    }

    private boolean existsProjectWithName(String name, String idToExclude) {
        return projectRepository
                .findByName(name)
                .filter(p -> !Objects.equals(p.getId(), idToExclude))
                .isPresent();
    }
}
