package com.projectmanagement.repository.interfaces;

import com.projectmanagement.domain.entity.ProjectEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;


public interface IProjectRepository  extends JpaRepository<ProjectEntity, Long> {


}
