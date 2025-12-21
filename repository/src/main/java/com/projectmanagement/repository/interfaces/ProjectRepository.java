package com.projectmanagement.repository.interfaces;

import com.projectmanagement.domain.entity.ProjectEntity;

import com.projectmanagement.domain.model.FilterProjectModel;
import com.projectmanagement.domain.model.LovModel;
import com.projectmanagement.domain.model.PieChartDataModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;


public interface ProjectRepository   extends JpaRepository<ProjectEntity, Long> , ProjectRepositoryCustom  {

    @Query("SELECT new com.projectmanagement.domain.model.PieChartDataModel(STR(p.statusId), COUNT(p)) " +
            "FROM ProjectEntity p GROUP BY p.statusId")
    List<PieChartDataModel> countProjectsByStatus();

    @Query("""
SELECT new com.projectmanagement.domain.model.PieChartDataModel(
       'CompletedManHour',
       SUM(task.completedManHour)
)
FROM TaskEntity task
WHERE (:projectId IS NULL OR task.projectId = :projectId)
""")
    List<PieChartDataModel> getCompletedManHour(@Param("projectId") Long projectId);
    @Query("""
    SELECT new com.projectmanagement.domain.model.PieChartDataModel(
        'RemainingManHour',
        COALESCE(SUM(task.totalRequiredManHour),0)
        - COALESCE(SUM(task.completedManHour),0)
    )
    FROM TaskEntity task
    WHERE (:projectId IS NULL OR task.projectId = :projectId)
  
""")
    List<PieChartDataModel> getRemainingManHour(@Param("projectId") Long projectId );

    @Query("SELECT new com.projectmanagement.domain.model.PieChartDataModel(STR(task.statusId),COUNT(*)) " +
            "FROM TaskEntity task GROUP BY task.statusId")
    List<PieChartDataModel> getProjectTaskStatus();

    @Query("SELECT new com.projectmanagement.domain.model.LovModel(project.id,project.name) " +
            "FROM ProjectEntity project")
    List<LovModel> getProjectLovModel();
}
