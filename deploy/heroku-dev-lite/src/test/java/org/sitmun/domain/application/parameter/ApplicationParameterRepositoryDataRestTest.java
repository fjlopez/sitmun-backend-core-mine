package org.sitmun.domain.application.parameter;

import org.junit.jupiter.api.Test;
import org.sitmun.test.Fixtures;
import org.sitmun.test.URIConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc

class ApplicationParameterRepositoryDataRestTest {

  @Autowired
  private MockMvc mvc;

  @Test
  void retrieveAll() throws Exception {
    mvc.perform(get(URIConstants.APPLICATION_PARAMETERS_URI_FILTERED, 1, "type", "PRINT_TEMPLATE")
        .with(user(Fixtures.admin()))
      )
      .andExpect(status().isOk())
      .andExpect(jsonPath("$._embedded.application-parameters", hasSize(4)));
  }

}
