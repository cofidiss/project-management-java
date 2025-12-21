package com.projectmanagement.service.mapper;

import com.projectmanagement.domain.entity.ProjectEntity;
import com.projectmanagement.domain.model.FilterProjectModel;
import com.projectmanagement.domain.model.LovModel;
import com.projectmanagement.service.DTO.AddProjectRequestDto;
import com.projectmanagement.service.DTO.FilterProjectDto;
import com.projectmanagement.service.DTO.LovDto;
import com.projectmanagement.service.DTO.ProjectInfoDto;
import com.projectmanagement.service.DTO.UpdateProjectRequestDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-21T16:45:43+0300",
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
        projectEntity.setDescription( addProjectRequestDto.getDescription() );

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
            projectEntity.setDescription( updateProjectRequestDto.getDescription() );
        }
        projectEntity.setActualStartDate( resolveActualStartDate(updateProjectRequestDto, projectEntityInDb) );
        projectEntity.setActualEndDate( resolveActualEndDate(updateProjectRequestDto, projectEntityInDb) );

        return projectEntity;
    }

    @Override
    public List<LovDto> toProjectLovDtoList(List<LovModel> lovModellist) {
        if ( lovModellist == null ) {
            return null;
        }

        List<LovDto> list = new ArrayList<LovDto>( lovModellist.size() );
        for ( LovModel lovModel : lovModellist ) {
            list.add( lovModelToLovDto( lovModel ) );
        }

        return list;
    }

    @Override
    public ProjectInfoDto toProjectInfoDto(ProjectEntity projectEntity) {
        if ( projectEntity == null ) {
            return null;
        }

        ProjectInfoDto projectInfoDto = new ProjectInfoDto();

        projectInfoDto.setId( projectEntity.getId() );
        projectInfoDto.setName( projectEntity.getName() );
        projectInfoDto.setActualStartDate( projectEntity.getActualStartDate() );
        projectInfoDto.setActualEndDate( projectEntity.getActualEndDate() );
        projectInfoDto.setPlannedStartDate( projectEntity.getPlannedStartDate() );
        projectInfoDto.setPlannedEndDate( projectEntity.getPlannedEndDate() );
        projectInfoDto.setCategoryId( projectEntity.getCategoryId() );
        projectInfoDto.setDescription( projectEntity.getDescription() );
        projectInfoDto.setStatusId( projectEntity.getStatusId() );

        projectInfoDto.setStatus( mapStatus(projectEntity.getStatusId()) );
        projectInfoDto.setCategory( mapCategory(projectEntity.getCategoryId()) );

        return projectInfoDto;
    }

    @Override
    public List<ProjectInfoDto> toProjectInfoDtoList(List<ProjectEntity> projectEntity) {
        if ( projectEntity == null ) {
            return null;
        }

        List<ProjectInfoDto> list = new ArrayList<ProjectInfoDto>( projectEntity.size() );
        for ( ProjectEntity projectEntity1 : projectEntity ) {
            list.add( toProjectInfoDto( projectEntity1 ) );
        }

        return list;
    }

    @Override
    public FilterProjectModel toFilterProjectModel(FilterProjectDto filterProjectDto) {
        if ( filterProjectDto == null ) {
            return null;
        }

        FilterProjectModel filterProjectModel = new FilterProjectModel();

        filterProjectModel.setPageNumber( filterProjectDto.getPageNumber() );
        filterProjectModel.setPageSize( filterProjectDto.getPageSize() );
        filterProjectModel.setName( filterProjectDto.getName() );
        filterProjectModel.setActualStartDate( filterProjectDto.getActualStartDate() );
        filterProjectModel.setActualEndDate( filterProjectDto.getActualEndDate() );
        filterProjectModel.setPlannedStartDate( filterProjectDto.getPlannedStartDate() );
        filterProjectModel.setPlannedEndDate( filterProjectDto.getPlannedEndDate() );
        filterProjectModel.setStatusId( filterProjectDto.getStatusId() );
        filterProjectModel.setCategoryId( filterProjectDto.getCategoryId() );

        return filterProjectModel;
    }

    protected LovDto lovModelToLovDto(LovModel lovModel) {
        if ( lovModel == null ) {
            return null;
        }

        LovDto lovDto = new LovDto();

        lovDto.setId( lovModel.getId() );
        lovDto.setName( lovModel.getName() );

        return lovDto;
    }
}
