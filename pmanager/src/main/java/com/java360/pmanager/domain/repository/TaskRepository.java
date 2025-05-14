package com.java360.pmanager.domain.repository;

import com.java360.pmanager.domain.entity.Task;
import com.java360.pmanager.domain.model.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, String> {

    @Query(
            """
            SELECT t
            FROM Task t
            WHERE
                (:projectId IS NULL OR t.project.id = :projectId) AND
                (:memberId IS NULL OR t.assignedMember.id = :memberId) AND
                (:status IS NULL OR t.status = :status) AND
                (:partialTitle IS NULL OR UPPER(t.title) LIKE CONCAT('%', UPPER(:partialTitle), '%')) 
            """
    )
//    List<Task> find(
//            @Param("projectId") String projectId,
//            @Param("memberId") String memberId,
//            @Param("status") TaskStatus status,
//            @Param("partialTitle") String partialTitle
//    );
    Page<Task> find(
            @Param("projectId") String projectId,
            @Param("memberId") String memberId,
            @Param("status") TaskStatus status,
            @Param("partialTitle") String partialTitle,
            Pageable pageable
    );
}
