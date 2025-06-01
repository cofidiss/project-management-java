package com.projectmanagement.service.mapper;

import com.projectmanagement.domain.entity.ProjectEntity;
import com.projectmanagement.service.DTO.AddProjectRequestDto;
import com.projectmanagement.service.DTO.UpdateProjectRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IProjectMapper {
    @Mapping(target = "name", source = "name")
    @Mapping(target = "plannedStartDate", source = "plannedStartDate")
    @Mapping(target = "plannedEndDate", source = "plannedEndDate")
    @Mapping(target = "categoryId", source = "categoryId")
    @Mapping(target = "statusId",expression = "java(com.projectmanagement.domain.enums.ProjectStatusEnum.New.ordinal())")
    ProjectEntity toProjectEntity(AddProjectRequestDto addProjectRequestDto);
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "plannedStartDate", source = "plannedStartDate")
    @Mapping(target = "plannedEndDate", source = "plannedEndDate")
    @Mapping(target = "actualStartDate", source = "actualStartDate")
    @Mapping(target = "actualEndDate", source = "actualEndDate")
    @Mapping(target = "categoryId", source = "categoryId")
    @Mapping(target = "statusId", source = "statusId")
    ProjectEntity toProjectEntity(UpdateProjectRequestDto updateProjectRequestDto);

}

