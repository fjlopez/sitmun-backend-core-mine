package org.sitmun.plugin.core.domain.projections;

import static org.hamcrest.Matchers.hasSize;
import static org.sitmun.plugin.core.test.TestConstants.SITMUN_ADMIN_USERNAME;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.sitmun.plugin.core.config.RepositoryRestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TaskProjectionTest {

  @Autowired
  private MockMvc mvc;

  @Test
  @WithMockUser(username = SITMUN_ADMIN_USERNAME)
  public void checkTaskGroupNameExistsInProjection() throws Exception {
    mvc.perform(get("/api/tasks?projection=view"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$._embedded.*.*", hasSize(1756)))
        .andExpect(jsonPath("$._embedded.*.[?(@.groupName)]", hasSize(1756)));
  }

  @Test
  @WithMockUser(username = SITMUN_ADMIN_USERNAME)
  public void checkCartographyGroupIdExistsInProjection() throws Exception {
    mvc.perform(get("/api/tasks?projection=view"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$._embedded.*.*", hasSize(1756)))
        .andExpect(jsonPath("$._embedded.*.[?(@.['group.id'])]", hasSize(1756)));
  }

  @TestConfiguration
  static class ContextConfiguration {

    @Bean
    public Validator validator() {
      return new LocalValidatorFactoryBean();
    }

    @Bean
    public RepositoryRestConfigurer repositoryRestConfigurer() {
      return new RepositoryRestConfig(validator());
    }
  }

}
