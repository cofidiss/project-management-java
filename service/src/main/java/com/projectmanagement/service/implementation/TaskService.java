package com.projectmanagement.service.implementation;

import com.projectmanagement.domain.entity.TaskEntity;
import com.projectmanagement.domain.model.GenericApiResponse;

import com.projectmanagement.repository.interfaces.ITaskRepository;
import com.projectmanagement.repository.interfaces.ProjectRepository;
import com.projectmanagement.repository.interfaces.ProjectRepositoryCustom;
import com.projectmanagement.service.DTO.AddTaskRequestDto;
import com.projectmanagement.service.DTO.UpdateTaskRequestDto;
import com.projectmanagement.service.interfaces.ITaskService;
import com.projectmanagement.service.mapper.IProjectMapper;
import com.projectmanagement.service.mapper.ITaskMapper;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Example;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

@Service
public class TaskService implements ITaskService {
    private ProjectRepository projectRepository;
    private ITaskRepository taskRepository;
    private ITaskMapper taskMapper;
    public TaskService(ITaskRepository taskRepository,ProjectRepository projectRepository,ITaskMapper taskMapper){
        this.taskMapper = taskMapper;
        this.taskRepository  = taskRepository;
        this.projectRepository = projectRepository;
    }
    @Override
    public GenericApiResponse<String, String> addTask(AddTaskRequestDto addTaskRequestDto) {
        var genericApiResponse = new GenericApiResponse<String, String>();
        var optProject =   projectRepository.findById(addTaskRequestDto.getProjectId());

        if(optProject.isEmpty()){
            genericApiResponse.setStatusCode(404);
            genericApiResponse.setErrorData("project not found");
            return  genericApiResponse;
        }
       var taskList =  taskRepository.findTaskswithSameProjectIdParentTaskIdAndGreatOrEqualPriority(addTaskRequestDto.getProjectId(),
                addTaskRequestDto.getPriority(),addTaskRequestDto.getParentTaskId());

        for (int i = 0; i < taskList.size(); i++) {
var taskEntityToUpdate = taskList.get(i);
            taskEntityToUpdate.setPriority(taskEntityToUpdate.getPriority() + 1 );
            taskRepository.save(taskEntityToUpdate);
        }
var taskEntityToInsert = taskMapper.toTaskEntity(addTaskRequestDto);

        taskRepository.save(taskEntityToInsert);


        return genericApiResponse;
    }

@Transactional
    @Override
    public GenericApiResponse<String, String> updateTask(UpdateTaskRequestDto updateTaskRequestDto) {
        var genericApiResponse = new GenericApiResponse<String, String>();
        var optTask = taskRepository.findById(updateTaskRequestDto.getId());

        if(optTask.isEmpty()){
            genericApiResponse.setStatusCode(404);
            genericApiResponse.setErrorData("task not found");
            return  genericApiResponse;
        }

      var taskEntityInDb =   optTask.get();

        var sourceTaskList =  taskRepository.findTaskswithSameProjectIdParentTaskIdAndGreatOrEqualPriority(taskEntityInDb.getProjectId(),
                taskEntityInDb.getPriority(),taskEntityInDb.getParentTaskId());

        for (int i = 0; i < sourceTaskList.size(); i++) {
            var taskEntityToUpdate = sourceTaskList.get(i);
            taskEntityToUpdate.setPriority(taskEntityToUpdate.getPriority()  - 1 );
            taskRepository.save(taskEntityToUpdate);
        }


        var destinationTtaskList =  taskRepository.findTaskswithSameProjectIdParentTaskIdAndGreatOrEqualPriority(taskEntityInDb.getProjectId(),
                updateTaskRequestDto.getPriority(),updateTaskRequestDto.getParentTaskId());

        for (int i = 0; i < destinationTtaskList.size(); i++) {
            var taskEntityToUpdate = destinationTtaskList.get(i);
            taskEntityToUpdate.setPriority(taskEntityToUpdate.getPriority()  + 1 );
            taskRepository.save(taskEntityToUpdate);
        }

        var taskEntityToUpdate = taskMapper.toTaskEntity(updateTaskRequestDto,taskEntityInDb);

        taskRepository.save(taskEntityToUpdate);


        return genericApiResponse;
    }

@Transactional
    @Override
    public GenericApiResponse<String, String> deleteTask(Long id) {
        var genericApiResponse = new GenericApiResponse<String, String>();
        var optTask = taskRepository.findById(id);

        if(optTask.isEmpty()){
            genericApiResponse.setStatusCode(404);
            genericApiResponse.setErrorData("task not found");
            return  genericApiResponse;
        }

        var taskEntityToDelete =   optTask.get();
    RecursiveTaskDelete(taskEntityToDelete);

        return genericApiResponse;
    }

    private  void RecursiveTaskDelete(TaskEntity taskEntity){
        taskRepository.delete(taskEntity);
    var childTasksToDelete =     taskRepository.findByParentTaskId(taskEntity.getId());
    if(childTasksToDelete.isEmpty())
        return;

    childTasksToDelete.forEach(this::RecursiveTaskDelete);
    }
}
