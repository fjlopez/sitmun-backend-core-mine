package org.sitmun.domain.task;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sitmun.domain.task.availability.TaskAvailability;
import org.sitmun.domain.task.availability.TaskAvailabilityRepository;
import org.sitmun.domain.territory.Territory;
import org.sitmun.domain.territory.TerritoryRepository;
import org.sitmun.test.BaseTest;
import org.sitmun.test.URIConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.sitmun.test.TestUtils.asJsonString;
import static org.sitmun.test.TestUtils.withMockSitmunAdmin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("Task Repository Data REST Test")
class TaskRepositoryDataRestTest extends BaseTest {

  private static final String TASK_NAME = "Task Name";

  @Autowired
  TaskRepository taskRepository;

  @Autowired
  TaskAvailabilityRepository taskAvailabilityRepository;

  @Autowired
  TerritoryRepository territoryRepository;

  private Territory territory;
  private Task task;
  private ArrayList<Task> tasks;
  private ArrayList<TaskAvailability> availabilities;

  @BeforeEach
  @WithMockUser(roles = {"ADMIN"})
  void init() {

      territory = Territory.builder()
        .name("Territorio 1")
        .code("")
        .blocked(false)
        .build();
      territoryRepository.save(territory);
      tasks = new ArrayList<>();

      Map<String, Object> parameters = new HashMap<>();
      parameters.put("string", "value");
      parameters.put("real", 1.0);
      parameters.put("integer", 1);
      parameters.put("array", new String[]{"one", "two", "three"});

      task = Task.builder()
        .name(TASK_NAME)
        .properties(parameters)
        .build();
      tasks.add(task);
      Task taskWithAvailabilities = new Task();
      taskWithAvailabilities.setName("Task with availabilities");
      tasks.add(taskWithAvailabilities);
      taskRepository.saveAll(tasks);

      availabilities = new ArrayList<>();
      TaskAvailability taskAvailability1 = new TaskAvailability();
      taskAvailability1.setTask(taskWithAvailabilities);
      taskAvailability1.setTerritory(territory);
      taskAvailability1.setCreatedDate(new Date());
      availabilities.add(taskAvailability1);
      taskAvailabilityRepository.saveAll(availabilities);
  }

  @AfterEach
  @WithMockUser(roles = {"ADMIN"})
  void cleanup() {
      taskAvailabilityRepository.deleteAll(availabilities);
      taskRepository.deleteAll(tasks);
      territoryRepository.delete(territory);
  }

  @Test
  @WithMockUser(roles = {"ADMIN"})
  @DisplayName("Create a new task")
  void postTask() throws Exception {
    String location = mvc.perform(post(URIConstants.TASKS_URI)
        .contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(task))
      ).andExpect(status().isCreated())
      .andReturn().getResponse().getHeader("Location");

    Assertions.assertThat(location).isNotNull();

    mvc.perform(get(location))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaTypes.HAL_JSON))
      .andExpect(jsonPath("$.name", equalTo(TASK_NAME)))
      .andExpect(jsonPath("$.properties.string", equalTo("value")));

    withMockSitmunAdmin(() -> {
      String[] paths = URI.create(location).getPath().split("/");
      Integer id = Integer.parseInt(paths[paths.length - 1]);
      taskRepository.findById(id).ifPresent((it) -> tasks.add(it));
    });
  }

  @Test
  @WithMockUser(roles = {"ADMIN"})
  @DisplayName("Get tasks per application")
  void getTasksAvailableForApplication() throws Exception {
    mvc.perform(get(URIConstants.TASKS_AVAILABLE_URI, 1))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$._embedded.tasks", hasSize(1289)));

    mvc.perform(get(URIConstants.TASKS_AVAILABLE_URI, 2))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$._embedded.tasks", hasSize(446)));
  }

  @Test
  @DisplayName("This endpoint is disabled for anonymous access")
  void getTasksAsPublic() throws Exception {
    mvc.perform(get(URIConstants.TASKS_URI))
      .andExpect(status().isUnauthorized());
  }

  @Test
  @WithMockUser(roles = {"ADMIN"})
  @DisplayName("This endpoint is enabled for ROLE_ADMIN")
  void getTasksAsSitmunAdmin() throws Exception {
    mvc.perform(get(URIConstants.TASKS_URI_PROJECTION_VIEW))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$._embedded.tasks", hasSize(1758)));
  }

  @Test
  @WithMockUser(roles = {"ADMIN"})
  @DisplayName("Access enabled to the cartography of a task")
  void getCartographyView() throws Exception {
    mvc.perform(get(URIConstants.TASK_PROJECTION_CARTOGRAPHY_VIEW, 3310))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.id", is(88)));
  }

  @Test
  @WithMockUser(roles = {"ADMIN"})
  @DisplayName("Tasks can be filtered")
  void getTaskFilteredByTypeAsSitmunAdmin() throws Exception {
    mvc.perform(get(URIConstants.TASKS_URI_FILTER, "type.id", "2", "10"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$._embedded.tasks", hasSize(10)));
  }

  @Test
  @DisplayName("This endpoint is disabled for anonymous creation")
  void postTaskAsPublicUserFails() throws Exception {
    mvc.perform(post(URIConstants.TASKS_URI)
      .contentType(MediaType.APPLICATION_JSON)
      .content(asJsonString(task))
    ).andExpect(status().is4xxClientError()).andReturn();
  }

  @Test
  @WithMockUser(roles = {"ADMIN"})
  @DisplayName("Access enabled to the roles of a task")
  void getRolesOfATask() throws Exception {
    mvc.perform(get(URIConstants.TASK_ROLE_URI, 1))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$._embedded.roles", hasSize(39)));
  }

}
