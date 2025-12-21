package com.projectmanagement.service.mapper;

import com.projectmanagement.domain.entity.ProjectEntity;

import com.projectmanagement.domain.enums.ProjectCategoryEnum;
import com.projectmanagement.domain.enums.ProjectStatusEnum;

import com.projectmanagement.domain.model.FilterProjectModel;
import com.projectmanagement.domain.model.LovModel;
import com.projectmanagement.service.DTO.*;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring")
public interface IProjectMapper extends IBaseMapper {
    @Mapping(target = "name", source = "name")
    @Mapping(target = "plannedStartDate", source = "plannedStartDate")
    @Mapping(target = "plannedEndDate", source = "plannedEndDate")
    @Mapping(target = "categoryId", source = "categoryId")
    @Mapping(target = "statusId",expression = "java(com.projectmanagement.domain.enums.ProjectStatusEnum.New.ordinal())")
    @Mapping(target = "description", source = "description")
    ProjectEntity toProjectEntity(AddProjectRequestDto addProjectRequestDto);



    @Mapping(target = "id", source = "updateProjectRequestDto.id")
    @Mapping(target = "name", source = "updateProjectRequestDto.name")
    @Mapping(target = "plannedStartDate", source = "updateProjectRequestDto.plannedStartDate")
    @Mapping(target = "plannedEndDate", source = "updateProjectRequestDto.plannedEndDate")
    @Mapping(target = "actualStartDate",expression = "java(resolveActualStartDate(updateProjectRequestDto, projectEntityInDb))")
    @Mapping(target = "actualEndDate",expression = "java(resolveActualEndDate(updateProjectRequestDto, projectEntityInDb))")
    @Mapping(target = "categoryId", source = "updateProjectRequestDto.categoryId")
    @Mapping(target = "statusId", source = "updateProjectRequestDto.statusId")
    @Mapping(target = "description", source = "updateProjectRequestDto.description")
    ProjectEntity toProjectEntity(UpdateProjectRequestDto updateProjectRequestDto, ProjectEntity projectEntityInDb);
    default LocalDateTime resolveActualStartDate(UpdateProjectRequestDto updateProjectRequestDto, ProjectEntity projectEntity) {
        if (updateProjectRequestDto.getStatusId() != ProjectStatusEnum.New.ordinal() && projectEntity.getStatusId() == ProjectStatusEnum.New.ordinal() ) {

            return LocalDateTime.now();
        }
        return projectEntity.getActualStartDate();
    }
    default LocalDateTime resolveActualEndDate(UpdateProjectRequestDto updateProjectRequestDto, ProjectEntity projectEntity) {
        if (updateProjectRequestDto.getStatusId() == ProjectStatusEnum.Completed.ordinal()  && projectEntity.getStatusId() != ProjectStatusEnum.Completed.ordinal() ) {

            return LocalDateTime.now();
        }
        return projectEntity.getActualEndDate();
    }

    List<LovDto> toProjectLovDtoList(List<LovModel> lovModellist);

    @Mapping(target = "status", expression = "java(mapStatus(projectEntity.getStatusId()))")
    @Mapping(target = "category", expression = "java(mapCategory(projectEntity.getCategoryId()))")
    ProjectInfoDto toProjectInfoDto(ProjectEntity projectEntity);
    List<ProjectInfoDto> toProjectInfoDtoList(List<ProjectEntity> projectEntity);


    FilterProjectModel toFilterProjectModel(FilterProjectDto filterProjectDto);

    default String mapStatus(int statusId) {
        return ProjectStatusEnum.values()[statusId].name();
    }

    default String mapCategory(int categoryId) {
        return ProjectCategoryEnum.values()[categoryId].name();
    }

}

