package org.sitmun.plugin.core.domain.projections;


import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import org.sitmun.plugin.core.domain.Task;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

/**
 * Projections for REST views of a task.
 */
@Projection(name = "view", types = {Task.class})
public interface TaskProjection {

  @Value("#{target.id}")
  Long getId();

  @Value("#{target.name}")
  String getName();

  @Value("#{target.createdDate}")
  Date getCreatedDate();

  @Value("#{target.order}")
  Integer getOrder();

  @Value("#{target.group != null? target.group.name : null }")
  String getGroupName();

  @Value("#{target.group != null? target.group.id : null }")
  @JsonProperty("group.id")
  Long getGroupId();
}
