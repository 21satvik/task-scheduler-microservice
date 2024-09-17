package com.zyntask.task.resource;

import com.zyntask.task.entity.Task;
import com.zyntask.task.service.TaskService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * RESTful resource for managing task entities.
 *
 * This resource provides operations for creating, retrieving, updating, and deleting tasks.
 * It uses {@link TaskService} to perform the actual operations on the task data.
 *
 * Annotations:
 * - @Path: Defines the base URI for all resource URIs provided by this resource class.
 * - @Produces: Specifies the media types that the methods of this resource can produce.
 * - @Consumes: Specifies the media types that the methods of this resource can consume.
 * - @RequestScoped: Specifies that the resource is request-scoped.
 * - @Inject: Injects dependencies.
 */
@Path("/tasks")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class TaskResource {

    @Inject
    private TaskService taskService;

    @Inject
    public TaskResource(TaskService taskService) {
        this.taskService = taskService;
    }


    /**
     * Retrieves all tasks associated with a specified username.
     *
     * @param username the username specified in the headers to retrieve tasks for
     * @return a Response object containing a list of tasks if available,
     *         an empty list if no tasks are found, or a BAD_REQUEST status if the username is not provided
     */
    @GET
    public Response getAllTasks(@HeaderParam("username") String username) {
        if (username == null || username.trim().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Username is required in the headers").build();
        }

        List<Task> tasks = taskService.getTasksByUsername(username);

        if (tasks.isEmpty()) {
            return Response.ok(Collections.emptyList()).build();
        }

        return Response.ok(tasks).build();
    }


    /**
     * Retrieves a task by its unique identifier.
     *
     * This method fetches a task based on the provided ID from the task service.
     * If the task is found, it returns an HTTP 200 OK response with the task entity.
     * If the task is not found, it returns an HTTP 404 NOT FOUND response.
     *
     * @param id the unique identifier of the task to retrieve
     * @return a Response object containing the task entity if found, or an HTTP 404 status if not found
     */
    @GET
    @Path("/{id}")
    public Response getTaskById(@PathParam("id") int id) {
        Optional<Task> task = taskService.getTaskById(id);
        if (task.isPresent()) {
            return Response.ok(task.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }



    /**
     * Creates a new task.
     *
     * @param task the task to be created
     * @return a Response object indicating the outcome of the create operation
     */
    @POST
    public Response createTask(Task task) {
        taskService.createTask(task);
        return Response.status(Response.Status.CREATED).build();
    }

    /**
     * Updates an existing task with the provided details.
     *
     * @param id the ID of the task to update
     * @param updatedTask the task object containing updated details
     * @return a Response object with the status of the update operation
     */
    @PUT
    @Path("/{id}")
    public Response updateTask(@PathParam("id") int id, Task updatedTask) {
        boolean updated = taskService.updateTask(id, updatedTask);
        if (updated) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }


    /**
     * Deletes a task by its ID.
     *
     * This method attempts to delete a task from the task service. If the task is successfully deleted,
     * a 204 No Content response is returned. If the task is not found, a 404 Not Found response is returned.
     *
     * @param id the ID of the task to be deleted
     * @return a Response object indicating the outcome of the delete operation
     */
    @DELETE
    @Path("/{id}")
    public Response deleteTask(@PathParam("id") int id) {
        boolean deleted = taskService.deleteTask(id);
        if (deleted) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}