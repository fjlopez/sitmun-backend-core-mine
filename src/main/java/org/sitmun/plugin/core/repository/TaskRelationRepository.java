package org.sitmun.plugin.core.repository;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.sitmun.plugin.core.domain.TaskRelation;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;

import java.util.Optional;

@Tag(name = "task relation")
@RepositoryRestResource(collectionResourceRel = "task-relations", path = "task-relations")
public interface TaskRelationRepository extends
  PagingAndSortingRepository<TaskRelation, Integer> {

  @Override
  @PreAuthorize("hasPermission(#entity, 'administration') or hasPermission(#entity, 'write')")
  @NonNull
  <S extends TaskRelation> S save(@P("entity") @NonNull S entity);

  @Override
  @PreAuthorize("hasPermission(#entity, 'administration') or hasPermission(#entity,  'delete')")
  void delete(@P("entity") @NonNull TaskRelation entity);

  @Override
  @PreAuthorize("hasPermission(#entityId, 'org.sitmun.plugin.core.domain.TaskRelation','administration') or hasPermission(#entityId, 'org.sitmun.plugin.core.domain.TaskRelation', 'delete')")
  void deleteById(@P("entityId") @NonNull Integer entityId);

  @Override
  @PostFilter("hasPermission(filterObject, 'administration') or hasPermission(filterObject, 'read')")
  @NonNull
  Iterable<TaskRelation> findAll();

  @Override
  @PreAuthorize("hasPermission(#entityId, 'org.sitmun.plugin.core.domain.TaskRelation','administration') or hasPermission(#entityId, 'org.sitmun.plugin.core.domain.TaskRelation', 'read')")
  @NonNull
  Optional<TaskRelation> findById(@P("entityId") @NonNull Integer entityId);


}