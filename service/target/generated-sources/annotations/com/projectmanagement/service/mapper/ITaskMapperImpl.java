package com.projectmanagement.service.mapper;

import com.projectmanagement.domain.entity.TaskEntity;
import com.projectmanagement.service.DTO.AddTaskRequestDto;
import com.projectmanagement.service.DTO.TaskInfoDto;
import com.projectmanagement.service.DTO.UpdateTaskRequestDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-16T16:08:19+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (JetBrains s.r.o.)"
)
@Component
public class ITaskMapperImpl implements ITaskMapper {

    @Override
    public TaskInfoDto toTaskInfoDto(TaskEntity taskEntity) {
        if ( taskEntity == null ) {
            return null;
        }

        TaskInfoDto taskInfoDto = new TaskInfoDto();

        taskInfoDto.setId( taskEntity.getId() );
        taskInfoDto.setName( taskEntity.getName() );
        taskInfoDto.setTotalRequiredManHour( taskEntity.getTotalRequiredManHour() );
        taskInfoDto.setCompletedManHour( taskEntity.getCompletedManHour() );
        taskInfoDto.setActualStartDate( taskEntity.getActualStartDate() );
        taskInfoDto.setActualEndDate( taskEntity.getActualEndDate() );
        taskInfoDto.setPlannedStartDate( taskEntity.getPlannedStartDate() );
        taskInfoDto.setPlannedEndDate( taskEntity.getPlannedEndDate() );
        taskInfoDto.setProjectId( taskEntity.getProjectId() );
        taskInfoDto.setParentTaskId( taskEntity.getParentTaskId() );
        taskInfoDto.setPriority( taskEntity.getPriority() );
        taskInfoDto.setStatusId( taskEntity.getStatusId() );

        taskInfoDto.setStatus( mapStatus(taskEntity.getStatusId()) );

        return taskInfoDto;
    }

    @Override
    public List<TaskInfoDto> toTaskInfoDtoList(List<TaskEntity> taskEntity) {
        if ( taskEntity == null ) {
            return null;
        }

        List<TaskInfoDto> list = new ArrayList<TaskInfoDto>( taskEntity.size() );
        for ( TaskEntity taskEntity1 : taskEntity ) {
            list.add( toTaskInfoDto( taskEntity1 ) );
        }

        return list;
    }

    @Override
    public TaskEntity toTaskEntity(AddTaskRequestDto addTaskRequestDto) {
        if ( addTaskRequestDto == null ) {
            return null;
        }

        TaskEntity taskEntity = new TaskEntity();

        taskEntity.setName( addTaskRequestDto.getName() );
        taskEntity.setPlannedStartDate( addTaskRequestDto.getPlannedStartDate() );
        taskEntity.setPlannedEndDate( addTaskRequestDto.getPlannedEndDate() );
        taskEntity.setPriority( addTaskRequestDto.getPriority() );
        taskEntity.setCategoryId( addTaskRequestDto.getCategoryId() );
        if ( addTaskRequestDto.getProjectId() != null ) {
            taskEntity.setProjectId( addTaskRequestDto.getProjectId() );
        }
        taskEntity.setParentTaskId( addTaskRequestDto.getParentTaskId() );
        taskEntity.setTotalRequiredManHour( addTaskRequestDto.getTotalRequiredManHour() );

        taskEntity.setCompletedManHour( 0.0 );
        taskEntity.setStatusId( com.projectmanagement.domain.enums.TaskStatusEnum.New.ordinal() );

        return taskEntity;
    }

    @Override
    public TaskEntity toTaskEntity(UpdateTaskRequestDto updateTaskRequestDto, TaskEntity taskEntityInDb) {
        if ( updateTaskRequestDto == null && taskEntityInDb == null ) {
            return null;
        }

        TaskEntity taskEntity = new TaskEntity();

        if ( updateTaskRequestDto != null ) {
            if ( updateTaskRequestDto.getId() != null ) {
                taskEntity.setId( updateTaskRequestDto.getId() );
            }
            taskEntity.setName( updateTaskRequestDto.getName() );
            taskEntity.setPlannedStartDate( updateTaskRequestDto.getPlannedStartDate() );
            taskEntity.setPlannedEndDate( updateTaskRequestDto.getPlannedEndDate() );
            taskEntity.setPriority( updateTaskRequestDto.getPriority() );
            taskEntity.setCategoryId( updateTaskRequestDto.getCategoryId() );
            taskEntity.setParentTaskId( updateTaskRequestDto.getParentTaskId() );
            taskEntity.setTotalRequiredManHour( updateTaskRequestDto.getTotalRequiredManHour() );
            taskEntity.setCompletedManHour( updateTaskRequestDto.getCompletedManHour() );
            taskEntity.setStatusId( updateTaskRequestDto.getStatusId() );
        }
        if ( taskEntityInDb != null ) {
            taskEntity.setProjectId( taskEntityInDb.getProjectId() );
        }
        taskEntity.setActualStartDate( resolveActualStartDate(updateTaskRequestDto, taskEntityInDb) );
        taskEntity.setActualEndDate( resolveActualEndDate(updateTaskRequestDto, taskEntityInDb) );

        return taskEntity;
    }
}
