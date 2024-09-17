package com.zyntask.task.repository;

import com.zyntask.task.config.EntityManagerFactoryProvider;
import com.zyntask.task.entity.Task;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.*;

import java.util.List;
import java.util.Optional;

/**
 * Repository class for managing Task entities.
 *
 * This class provides methods for CRUD operations on tasks,
 * including finding all tasks, finding tasks by ID, saving
 * (creating or updating) tasks, and deleting tasks by ID.
 */
@ApplicationScoped
public class TaskRepository {

    private final EntityManagerFactory entityManagerFactory = EntityManagerFactoryProvider.getEntityManagerFactory();
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();

    /**
     * Retrieves a list of all Task entities from the database.
     *
     * @return a list of Task objects representing all tasks available in the database.
     *         If no tasks are found, an empty list is returned.
     * @throws RuntimeException if an error occurs while fetching the tasks.
     */
    public List<Task> findAll() {
        try {
            return entityManager.createNamedQuery("getAllTasks", Task.class).getResultList();
        } catch (Exception e) {
            // Handle or log the exception
            throw new RuntimeException("Failed to fetch all tasks", e);
        }
    }

    /**
     * Finds a task by its unique identifier.
     *
     * @param id the unique identifier of the task to retrieve
     * @return an Optional containing the task if found, or an empty Optional if not found
     */
    public Optional<Task> findById(int id) {
        try {
            Task task = entityManager.find(Task.class, id);
            return task != null ? Optional.of(task) : Optional.empty();
        } catch (Exception e) {
            // Handle or log the exception
            throw new RuntimeException("Failed to find task by ID", e);
        }
    }


    /**
     * Finds tasks associated with the specified username.
     *
     * @param username the username to filter tasks by
     * @return a list of Task objects belonging to the specified username.
     *         If no tasks are found, an empty list is returned.
     */
    public List<Task> findByUsername(String username) {
        try {
            return entityManager.createNamedQuery("getTasksByUsername", Task.class)
                    .setParameter("username", username)
                    .getResultList();
        } catch (NoResultException e) {
            return List.of(); // Return an empty list if no tasks found
        }
    }

    /**
     * Saves the given task to the database.
     *
     * If the task is new (id is 0), it is persisted as a new entity.
     * If the task already exists (id is not 0), it is updated.
     *
     * @param task the task object to be saved or updated in the database
     */
    public void save(Task task) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            if (task.getId() == 0) {
                entityManager.persist(task);  // New task
            } else {
                entityManager.merge(task);  // Existing task
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            // Handle or log the exception
            throw new RuntimeException("Failed to save task", e);
        }
    }

    /**
     * Deletes a task from the repository by its unique identifier.
     *
     * This method attempts to find the task with the given ID in the database.
     * If the task exists, it is removed, and the transaction is committed.
     * In case of any errors during the process, the transaction is rolled back.
     *
     * @param id the unique identifier of the task to be deleted
     * @return true if the task was successfully deleted, false if the task was not found
     */
    public boolean deleteById(int id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Task task = entityManager.find(Task.class, id);
            if (task != null) {
                entityManager.remove(task);
                transaction.commit();
                return true;
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            // Handle or log the exception
            throw new RuntimeException("Failed to delete task by ID", e);
        }
        return false;
    }
}