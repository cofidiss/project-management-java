package com.projectmanagement.repository.interfaces;

import com.projectmanagement.domain.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ITaskRepository extends JpaRepository<TaskEntity, Long> {
    @Query("SELECT task FROM TaskEntity task WHERE task.projectId = :projectId AND task.parentTaskId = :parentTaskId AND task.priority >= :priority")
    List<TaskEntity> findTaskswithSameProjectIdParentTaskIdAndGreatOrEqualPriority(@Param("projectId") Long projectId, @Param("priority") int priority, @Param("parentTaskId") Long parentTaskId);
    @Query("SELECT task FROM TaskEntity task WHERE task.parentTaskId = :id")
    List<TaskEntity>  findByParentTaskId(Long id);
    @Query("SELECT task FROM TaskEntity task WHERE task.projectId = :id")
    List<TaskEntity>   findByProjectId(long id);
}


