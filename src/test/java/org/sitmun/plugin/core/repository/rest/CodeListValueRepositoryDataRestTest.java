package org.sitmun.plugin.core.repository.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.sitmun.plugin.core.config.RepositoryRestConfig;
import org.sitmun.plugin.core.test.URIConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
public class CodeListValueRepositoryDataRestTest {

  @Autowired
  private MockMvc mvc;

  @Test
  public void filterType() throws Exception {
    mvc.perform(get(URIConstants.CODELIST_VALUES_URI_FILTER, "cartographyPermission.type"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$._embedded.*.*", hasSize(4)))
      .andExpect(jsonPath(
        "$._embedded.codelist-values[?(@.codeListName == 'cartographyPermission.type')]",
        hasSize(4)));
    mvc.perform(get(URIConstants.CODELIST_VALUES_URI_FILTER, "territory.scope"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$._embedded.codelist-values.*", hasSize(3)))
      .andExpect(jsonPath("$._embedded.codelist-values[?(@.codeListName == 'territory.scope')]",
        hasSize(3)));
  }

  @Test
  public void cantCreateSystemCodeListValue() throws Exception {
    String body = "{\"value\":\"A\", \"codeListName\":\"B\", \"system\":true}";
    mvc.perform(post(URIConstants.CODELIST_VALUES_URI).content(body))
      .andExpect(status().isBadRequest());
  }

  @Test
  public void cantModifySystemCodeListValue() throws Exception {
    String body = "{\"value\":\"A\", \"codeListName\":\"B\", \"system\":false}";
    mvc.perform(put(URIConstants.CODELIST_VALUE_URI, 31).content(body))
      .andExpect(status().isBadRequest());
  }

  @Test
  public void cantDeleteCodeListValue() throws Exception {
    mvc.perform(delete(URIConstants.CODELIST_VALUE_URI, 31))
      .andExpect(status().isBadRequest());
  }

  @TestConfiguration
  static class ContextConfiguration {
    @Bean
    public Validator validator() {
      return new LocalValidatorFactoryBean();
    }

    @Bean
    RepositoryRestConfigurer repositoryRestConfigurer() {
      return new RepositoryRestConfig(validator());
    }
  }

}
