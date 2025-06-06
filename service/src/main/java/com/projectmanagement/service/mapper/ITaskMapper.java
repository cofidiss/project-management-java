package com.projectmanagement.service.mapper;

import com.projectmanagement.domain.entity.ProjectEntity;
import com.projectmanagement.domain.entity.TaskEntity;
import com.projectmanagement.domain.enums.TaskStatusEnum;
import com.projectmanagement.service.DTO.AddProjectRequestDto;
import com.projectmanagement.service.DTO.AddTaskRequestDto;
import com.projectmanagement.service.DTO.UpdateProjectRequestDto;
import com.projectmanagement.service.DTO.UpdateTaskRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface ITaskMapper {



    @Mapping(target = "name", source = "name")
    @Mapping(target = "plannedStartDate", source = "plannedStartDate")
    @Mapping(target = "plannedEndDate", source = "plannedEndDate")
    @Mapping(target = "priority", source = "priority")
    @Mapping(target = "categoryId", source = "categoryId")
    @Mapping(target = "projectId", source = "projectId")
    @Mapping(target = "parentTaskId", source = "parentTaskId")
    @Mapping(target = "completedManHour", expression = "java(0.0)")
    @Mapping(target = "totalRequiredManHour", source = "totalRequiredManHour")
    @Mapping(target = "statusId",expression = "java(com.projectmanagement.domain.enums.TaskStatusEnum.New.ordinal())")
    TaskEntity toTaskEntity(AddTaskRequestDto addTaskRequestDto);



    @Mapping(target = "id", source = "updateTaskRequestDto.id")
    @Mapping(target = "name", source = "updateTaskRequestDto.name")
    @Mapping(target = "plannedStartDate", source = "updateTaskRequestDto.plannedStartDate")
    @Mapping(target = "plannedEndDate", source = "updateTaskRequestDto.plannedEndDate")
    @Mapping(target = "actualStartDate",expression = "java(resolveActualStartDate(updateTaskRequestDto, taskEntityInDb))")
    @Mapping(target = "actualEndDate",expression = "java(resolveActualEndDate(updateTaskRequestDto, taskEntityInDb))")
    @Mapping(target = "priority", source = "updateTaskRequestDto.priority")
    @Mapping(target = "categoryId", source = "updateTaskRequestDto.categoryId")
    @Mapping(target = "projectId", source = "taskEntityInDb.projectId")
    @Mapping(target = "parentTaskId", source = "updateTaskRequestDto.parentTaskId")
    @Mapping(target = "totalRequiredManHour", source = "updateTaskRequestDto.totalRequiredManHour")
    @Mapping(target = "completedManHour", source = "updateTaskRequestDto.completedManHour")
    @Mapping(target = "statusId",source = "updateTaskRequestDto.statusId")
    TaskEntity toTaskEntity(UpdateTaskRequestDto updateTaskRequestDto, TaskEntity taskEntityInDb);

    default LocalDateTime resolveActualStartDate(UpdateTaskRequestDto updateTaskRequestDto, TaskEntity taskEntityInDb) {
        if (updateTaskRequestDto.getStatusId() != TaskStatusEnum.New.ordinal() && taskEntityInDb.getStatusId() == TaskStatusEnum.New.ordinal() ) {

            return LocalDateTime.now();
        }
        return taskEntityInDb.getActualStartDate();
    }
    default LocalDateTime resolveActualEndDate(UpdateTaskRequestDto updateTaskRequestDto, TaskEntity taskEntityInDb) {
        if (updateTaskRequestDto.getStatusId() == TaskStatusEnum.Completed.ordinal()  && taskEntityInDb.getStatusId() != TaskStatusEnum.Completed.ordinal() ) {

            return LocalDateTime.now();
        }
        return taskEntityInDb.getActualEndDate();
    }
}
