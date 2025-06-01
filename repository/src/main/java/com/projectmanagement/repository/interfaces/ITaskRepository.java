package com.projectmanagement.repository.interfaces;

import com.projectmanagement.domain.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITaskRepository extends JpaRepository<TaskEntity, Long> {


}
