package ftn.upp.invertAuction.controllers;

import ftn.upp.invertAuction.dto.TaskInfoDTO;
import org.activiti.engine.FormService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/activiti")
public class ActivitiController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private FormService formService;

    @RequestMapping(value = "/getUserTasks/{username}", method = RequestMethod.GET)
    public ResponseEntity<List<TaskInfoDTO>> getUsersTasks(@PathVariable String username){
        System.out.println("USERNAME: " + username);
        List<TaskInfoDTO> result = new ArrayList<>();
        List<Task> tasks = taskService.createTaskQuery()
                .taskAssignee(username)
                .active()
                //.processDefinitionId("invertAuction")
                .list();
        for(Task t : tasks){
            TaskInfoDTO dto = new TaskInfoDTO(t.getName(),t.getId());
            result.add(dto);
            System.out.println("TASK: " + t.toString());
        }
        return new ResponseEntity<List<TaskInfoDTO>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/getUserTasksProperties/{taskId}", method = RequestMethod.GET)
    public ResponseEntity<List<FormProperty>> getFormProperty(@PathVariable String taskId){
        List<FormProperty> result = new ArrayList<>();
        TaskFormData taskFormData = formService.getTaskFormData(taskId);
        for(FormProperty prop : taskFormData.getFormProperties()){
            result.add(prop);
        }

        return new ResponseEntity<List<FormProperty>>(result,HttpStatus.OK);
    }
}
