package com.projectmanagement.repository.interfaces;

import com.projectmanagement.domain.entity.ProjectEntity;

import com.projectmanagement.domain.model.FilterProjectModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;


public interface ProjectRepository   extends JpaRepository<ProjectEntity, Long> , ProjectRepositoryCustom  {



}
