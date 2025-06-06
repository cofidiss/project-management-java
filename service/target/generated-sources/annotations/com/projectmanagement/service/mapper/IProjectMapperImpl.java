package com.projectmanagement.service.mapper;

import com.projectmanagement.domain.entity.ProjectEntity;
import com.projectmanagement.service.DTO.AddProjectRequestDto;
import com.projectmanagement.service.DTO.UpdateProjectRequestDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-06T14:14:56+0300",
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
    public ProjectEntity toProjectEntity(UpdateProjectRequestDto updateProjectRequestDto, ProjectEntity projectEntityInDb) {
        if ( updateProjectRequestDto == null && projectEntityInDb == null ) {
            return null;
        }

        ProjectEntity projectEntity = new ProjectEntity();

        if ( updateProjectRequestDto != null ) {
            projectEntity.setId( updateProjectRequestDto.getId() );
            projectEntity.setName( updateProjectRequestDto.getName() );
            projectEntity.setPlannedStartDate( updateProjectRequestDto.getPlannedStartDate() );
            projectEntity.setPlannedEndDate( updateProjectRequestDto.getPlannedEndDate() );
            projectEntity.setCategoryId( updateProjectRequestDto.getCategoryId() );
            projectEntity.setStatusId( updateProjectRequestDto.getStatusId() );
        }
        projectEntity.setActualStartDate( resolveActualStartDate(updateProjectRequestDto, projectEntityInDb) );
        projectEntity.setActualEndDate( resolveActualEndDate(updateProjectRequestDto, projectEntityInDb) );

        return projectEntity;
    }
}
