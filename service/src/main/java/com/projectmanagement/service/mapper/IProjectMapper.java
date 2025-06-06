package com.projectmanagement.service.mapper;

import com.projectmanagement.domain.entity.ProjectEntity;

import com.projectmanagement.domain.enums.ProjectStatusEnum;

import com.projectmanagement.service.DTO.AddProjectRequestDto;
import com.projectmanagement.service.DTO.UpdateProjectRequestDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface IProjectMapper {
    @Mapping(target = "name", source = "name")
    @Mapping(target = "plannedStartDate", source = "plannedStartDate")
    @Mapping(target = "plannedEndDate", source = "plannedEndDate")
    @Mapping(target = "categoryId", source = "categoryId")
    @Mapping(target = "statusId",expression = "java(com.projectmanagement.domain.enums.ProjectStatusEnum.New.ordinal())")
    ProjectEntity toProjectEntity(AddProjectRequestDto addProjectRequestDto);


    @Mapping(target = "id", source = "updateProjectRequestDto.id")
    @Mapping(target = "name", source = "updateProjectRequestDto.name")
    @Mapping(target = "plannedStartDate", source = "updateProjectRequestDto.plannedStartDate")
    @Mapping(target = "plannedEndDate", source = "updateProjectRequestDto.plannedEndDate")
    @Mapping(target = "actualStartDate",expression = "java(resolveActualStartDate(updateProjectRequestDto, projectEntityInDb))")
    @Mapping(target = "actualEndDate",expression = "java(resolveActualEndDate(updateProjectRequestDto, projectEntityInDb))")
    @Mapping(target = "categoryId", source = "updateProjectRequestDto.categoryId")
    @Mapping(target = "statusId", source = "updateProjectRequestDto.statusId")
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
}

