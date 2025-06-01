package com.projectmanagement.service.mapper;

import com.projectmanagement.domain.entity.ProjectEntity;
import com.projectmanagement.service.DTO.AddProjectRequestDto;
import com.projectmanagement.service.DTO.UpdateProjectRequestDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-01T14:00:02+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (JetBrains s.r.o.)"
)
@Component
public class IProjectMapperImpl implements IProjectMapper {

    @Override
    public ProjectEntity toProjectEntity(AddProjectRequestDto addProjectRequestDto) {
        if ( addProjectRequestDto == null ) {
            return null;
        }

        ProjectEntity projectEntity = new ProjectEntity();

        projectEntity.setName( addProjectRequestDto.getName() );
        projectEntity.setPlannedStartDate( addProjectRequestDto.getPlannedStartDate() );
        projectEntity.setPlannedEndDate( addProjectRequestDto.getPlannedEndDate() );
        projectEntity.setCategoryId( addProjectRequestDto.getCategoryId() );

        projectEntity.setStatusId( com.projectmanagement.domain.enums.ProjectStatusEnum.New.ordinal() );

        return projectEntity;
    }

    @Override
    public ProjectEntity toProjectEntity(UpdateProjectRequestDto updateProjectRequestDto) {
        if ( updateProjectRequestDto == null ) {
            return null;
        }

        ProjectEntity projectEntity = new ProjectEntity();

        projectEntity.setId( updateProjectRequestDto.getId() );
        projectEntity.setName( updateProjectRequestDto.getName() );
        projectEntity.setPlannedStartDate( updateProjectRequestDto.getPlannedStartDate() );
        projectEntity.setPlannedEndDate( updateProjectRequestDto.getPlannedEndDate() );
        projectEntity.setActualStartDate( updateProjectRequestDto.getActualStartDate() );
        projectEntity.setActualEndDate( updateProjectRequestDto.getActualEndDate() );
        projectEntity.setCategoryId( updateProjectRequestDto.getCategoryId() );
        projectEntity.setStatusId( updateProjectRequestDto.getStatusId() );

        return projectEntity;
    }
}
