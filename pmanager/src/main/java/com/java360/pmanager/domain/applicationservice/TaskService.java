package com.java360.pmanager.domain.applicationservice;

import com.java360.pmanager.domain.entity.Member;
import com.java360.pmanager.domain.entity.Project;
import com.java360.pmanager.domain.entity.Task;
import com.java360.pmanager.domain.exception.InvalidProjectStatusException;
import com.java360.pmanager.domain.exception.InvalidTaskStatusException;
import com.java360.pmanager.domain.exception.TaskNotFoundException;
import com.java360.pmanager.domain.model.ProjectStatus;
import com.java360.pmanager.domain.model.TaskStatus;
import com.java360.pmanager.domain.repository.TaskRepository;
import com.java360.pmanager.infrastructure.config.AppConfigProperties;
import com.java360.pmanager.infrastructure.dto.SaveTaskDataDTO;
import com.java360.pmanager.infrastructure.util.PaginationHelper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.java360.pmanager.domain.model.TaskStatus.PENDING;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final ProjectService projectService;
    private final MemberService memberService;
    private final TaskRepository taskRepository;
    private final AppConfigProperties prosp;

    @Transactional
    public Task createTask(SaveTaskDataDTO saveTaskData) {
        Project project = getProjectIfPossible(saveTaskData.getProjectId());
        Member member = getMemberIfPossible(saveTaskData.getMemberId());

        Task task = Task
                .builder()
                .title((saveTaskData.getTitle()))
                .description(saveTaskData.getDescription())
                .numberOfDays(saveTaskData.getNumberOfDays())
                .status(PENDING)
                .project(project)
                .assignedMember(member)
                .build();

        taskRepository.save(task);
        return task;
    }

    public Task loadTask(String taskId) {
        return taskRepository
                .findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));
    }

    @Transactional
    public void deleteTask(String taskId) {
        Task task = loadTask(taskId);
        taskRepository.delete(task);
    }

    @Transactional
    public Task updateTask(String taskId, SaveTaskDataDTO saveTaskData) {

        Project project = getProjectIfPossible(saveTaskData.getProjectId());
        Member member = getMemberIfPossible(saveTaskData.getMemberId());

        Task task = loadTask(taskId);

       task.setTitle(saveTaskData.getTitle());
       task.setDescription(saveTaskData.getDescription());
       task.setNumberOfDays(saveTaskData.getNumberOfDays());
       task.setStatus(convertToTaskStatus(saveTaskData.getStatus()));
       task.setProject(project);
       task.setAssignedMember(member);

       return task;
    }


    public Page<Task> findTasks(
            String projectId,
            String memberId,
            String statusStr,
            String partialTitle,
            Integer page,
            String directionStr,
            List<String> properties
    ){
        Sort sort = Sort.by(Sort.Direction.DESC,"title");

        return taskRepository.find(
                projectId,
                memberId,
                Optional.ofNullable(statusStr).map(this::convertToTaskStatus).orElse(null),
                partialTitle,
                PaginationHelper.createPageable(page, prosp.getGeneral().getPageSize(), directionStr, properties)
        );
    }

    private TaskStatus convertToTaskStatus(String statusStr) {
        try {
            return TaskStatus.valueOf(statusStr);
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new InvalidProjectStatusException(statusStr);
        }
    }

    private Member getMemberIfPossible(String memberId) {
        Member member = null;
        if (!Objects.isNull(memberId)) {
            member = memberService.loadMemberById(memberId);
        }
        return member;
    }

    private Project getProjectIfPossible(String projectId ) {
        Project project = null;
        if (!Objects.isNull(projectId)) {
            project = projectService.loadProject(projectId);
        }
        return project;
    }
}
