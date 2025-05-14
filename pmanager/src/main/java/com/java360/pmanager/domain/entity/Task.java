package com.java360.pmanager.domain.entity;

import com.java360.pmanager.domain.model.TaskStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = UUID)
    @Column(name = "id", nullable = false, length = 36)
    private String id;

    @Column(name = "title", nullable = false, length = 80)
    private String title;

    @Column(name = "description", nullable = false, length = 150)
    private String description;

    @Column(name = "number_of_days", nullable = false)
    private Integer numberOfDays;

    @Column(name = "status" , nullable = false)
    @Enumerated(STRING)
    private TaskStatus status;


    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "assigned_member")
    private Member assignedMember;
}
