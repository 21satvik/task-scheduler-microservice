package com.zyntask.task.service;

import com.zyntask.task.entity.Task;
import com.zyntask.task.repository.TaskRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing tasks.
 *
 * Provides methods to perform CRUD operations such as retrieving all tasks,
 * retrieving tasks by ID, creating new tasks, updating existing tasks, and
 * deleting tasks by ID.
 */
@ApplicationScoped
public class TaskService {


    private final TaskRepository taskRepository;

    @Inject
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * Retrieves a task by its unique identifier.
     *
     * @param id the unique identifier of the task to retrieve
     * @return an Optional containing the task if found, or an empty Optional if not found
     */
    public Optional<Task> getTaskById(int id) {
        return taskRepository.findById(id);
    }


    /**
     * Retrieves tasks associated with a specific username.
     *
     * @param username the username to filter tasks by
     * @return a list of Task objects belonging to the specified username
     */
    public List<Task> getTasksByUsername(String username) {
        return taskRepository.findByUsername(username);
    }


    /**
     * Persists a new task into the task repository.
     *
     * @param task the task object to be created and saved to the repository
     */
    public void createTask(Task task) {
        taskRepository.save(task);
    }

    /**
     * Updates an existing task with the provided details.
     *
     * @param id the ID of the task to update
     * @param updatedTask the task object containing updated details
     * @return true if the task was successfully updated, false if the task with the given ID was not found
     */
    public boolean updateTask(int id, Task updatedTask) {
        Optional<Task> taskOpt = taskRepository.findById(id);
        if (taskOpt.isPresent()) {
            Task task = taskOpt.get();
            task.setTaskName(updatedTask.getTaskName());
            task.setDescription(updatedTask.getDescription());
            task.setDeadline(updatedTask.getDeadline());
            taskRepository.save(task);
            return true;
        }
        return false;
    }

    /**
     * Deletes a task by its ID.
     *
     * This method attempts to delete a task from the TaskRepository. If the task is successfully
     * deleted, it returns true. If the task is not found, it returns false.
     *
     * @param id the ID of the task to be deleted
     * @return true if the task was successfully deleted, false otherwise
     */
    public boolean deleteTask(int id) {
        return taskRepository.deleteById(id);
    }
}