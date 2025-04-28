package com.java360.pmanager.domain.exception;

import com.java360.pmanager.infrastructure.exception.RequestException;

public class TaskNotFoundException extends RequestException {

    public TaskNotFoundException(String taskId) {
        super("TaskNotFound", "Task not found: " + taskId);
    }

}
