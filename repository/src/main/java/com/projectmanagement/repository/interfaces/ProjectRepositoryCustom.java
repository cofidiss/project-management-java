package com.projectmanagement.repository.interfaces;

import com.projectmanagement.domain.entity.ProjectEntity;
import com.projectmanagement.domain.model.FilterProjectModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProjectRepositoryCustom  {
    public List<ProjectEntity> filterrProjects(FilterProjectModel filter);
}
