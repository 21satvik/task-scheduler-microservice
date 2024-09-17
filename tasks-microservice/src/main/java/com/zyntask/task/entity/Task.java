package com.zyntask.task.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Represents a task entity with details such as user, task name, description, deadline, and creation time.
 *
 * This entity is mapped to the "Task" database table and includes multiple named queries for fetching tasks.
 *
 * Named Queries:
 * - getAllTasks: Retrieve all tasks
 * - getTasksByUsername: Retrieve tasks based on username
 *
 * Annotations:
 * - @Entity: Specifies that this class is an entity and is mapped to a database table.
 * - @Table: Specifies the name of the database table to be used for mapping.
 * - @Access: Defines the access type to be used (FIELD in this case).
 * - @NamedQueries: Defines named queries that can be used to retrieve instances of the entity from the database.
 * - @Id: Specifies the primary key of the entity.
 * - @GeneratedValue: Provides the specification of generation strategies for the values of primary keys.
 * - @Column: Used to specify the mapped column for a persistent property or field.
 * - @NotNull: Ensures that the annotated element is not null.
 * - @NotEmpty: Ensures that the annotated string is not null or empty.
 */
@Entity(name = "Task")
@Table(name = "Task")
@Access(AccessType.FIELD)
@NamedQueries({
        @NamedQuery(name = "getAllTasks",
                query = "SELECT t FROM Task t"),
        @NamedQuery(name = "getTasksByUsername",
                query = "SELECT t FROM Task t WHERE t.username = :username")
})
public class Task {

    /**
     * The unique identifier for the Task entity.
     * This field is auto-generated using the IDENTITY strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /**
     * Represents the name of the user associated with a task.
     *
     * This field is mapped to the "user_name" column in the database and must not be null.
     * Ensures that each task has a user associated and prevents tasks from being created
     * without a user.
     */
    @Column(name = "user_name", nullable = false)
    @NotNull(message = "User cannot be null")
    private String username;

    /**
     * Represents the name of the task.
     * This field is mandatory and cannot be empty.
     */
    @Column(name = "task_name", nullable = false)
    @NotEmpty(message = "Task name cannot be empty")
    private String taskName;

    /**
     * A brief description of the task.
     */
    @Column(name = "description")
    private String description;

    /**
     * The deadline for the task completion.
     * This field is a mandatory attribute and cannot be null.
     */
    @Column(name = "deadline")
    @NotNull(message = "Deadline cannot be null")
    private LocalDateTime deadline;

    /**
     * The timestamp when the task was created.
     * This field is mandatory and cannot be changed once set.
     * It is populated automatically at the time of task creation.
     */
    @Column(name = "created_at", nullable = false, updatable = false)
    @NotNull(message = "Creation time cannot be null")
    private LocalDateTime createdAt;

    /**
     * Constructs a new Task instance with default values.
     */
    public Task() {
    }

    /**
     * Constructs a new Task with specified user name, task name, description, deadline, and creation timestamp.
     *
     * @param username the name of the user who created the task
     * @param taskName the name of the task
     * @param description a detailed description of the task
     * @param deadline the deadline for the task completion
     * @param createdAt the creation timestamp of the task
     */
    public Task(String username, String taskName, String description, LocalDateTime deadline, LocalDateTime createdAt) {
        this.username = username;
        this.taskName = taskName;
        this.description = description;
        this.deadline = deadline;
        this.createdAt = createdAt;
    }

    /**
     * Retrieves the unique identifier for this task.
     *
     * @return the unique identifier (id) of the task.
     */
    public int getId() {
        return id;
    }

    /**
     * Retrieves the user name associated with this task.
     *
     * @return the user name of the task
     */
    public String getUserName() {
        return username;
    }

    /**
     * Sets the username for the task.
     *
     * @param username the username to set
     */
    public void setUserName(String username) {
        this.username = username;
    }

    /**
     * Retrieves the name of the task.
     *
     * @return the name of the task
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Sets the name of the task.
     *
     * @param taskName the new name of the task
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Retrieves the description of the task.
     *
     * @return the description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description for this task.
     *
     * @param description the description of the task
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Retrieves the deadline of the task.
     *
     * @return the LocalDateTime representing the deadline of the task
     */
    public LocalDateTime getDeadline() {
        return deadline;
    }

    /**
     * Sets the deadline for the task.
     *
     * @param deadline the deadline to be set for the task
     */
    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    /**
     * Retrieves the creation date and time of the task.
     *
     * @return the LocalDateTime when the task was created
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the creation timestamp for the task.
     *
     * @param createdAt the LocalDateTime when the task was created
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Returns a string representation of the Task object.
     *
     * @return a string containing the task's id, userName, taskName, description, deadline, and createdAt values.
     */
    @Override
    public String toString() {
        return "Tasks{id=" + id + ", userName=" + username + ", taskName='" + taskName + "', description='" + description + "', deadline=" + deadline + ", createdAt=" + createdAt + "}";
    }
}