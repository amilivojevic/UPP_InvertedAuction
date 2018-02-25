package ftn.upp.invertAuction.controllers;

import ftn.upp.invertAuction.dto.NewAgentDTO;
import ftn.upp.invertAuction.dto.NewUserDTO;
import org.activiti.engine.FormService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.StartFormData;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RegistrationController {

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    RepositoryService repositoryService;

    @Autowired
    FormService formService;

    @Autowired
    TaskService taskService;

    @RequestMapping(value = "/start-process", method = RequestMethod.GET)
    public String hello(){
        return "HELLO";
    }

    @RequestMapping(value = "/start-process", method = RequestMethod.POST)
    public String startNewInstance(@RequestBody NewUserDTO newUserDTO) {
        System.out.println("START NEW INSTANCE: ");
        Map<String, String> params = new HashMap<>();

        params.put("name", newUserDTO.getName());
        params.put("mail", newUserDTO.getMail());
        params.put("username", newUserDTO.getMail());

        //runtimeService.startProcessInstanceByKey("registrationProcess");
        //StartFormData startFormData = formService.getStartFormData("registrationProcess");
        //List<org.activiti.engine.form.FormProperty> formProperties = startFormData.getFormProperties();

        ProcessDefinition procDef = repositoryService.createProcessDefinitionQuery().processDefinitionKey("registrationProcess").latestVersion().singleResult();
        formService.submitStartFormData(procDef.getId(),params);
        //runtimeService.startProcessInstanceByKey("registrationProcess");

        return "Process started. Number of currently running"
                + "process instances = "
                + runtimeService.createProcessInstanceQuery().count();
    }

    @RequestMapping(value = "{username}/register-agent", method = RequestMethod.POST)
    public ResponseEntity registerAgentTask(@PathVariable String username,@RequestBody NewAgentDTO newAgentDTO){
        System.out.println("register-agent!");
        Map<String, String> params = new HashMap<>();

        params.put("name", newAgentDTO.getName());
        params.put("mail", newAgentDTO.getMail());
        params.put("username", newAgentDTO.getMail());

        String taskId = getLastTaskByAssignee(username).getId();

        formService.submitTaskFormData(taskId,params);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @RequestMapping(value = "/{username}/get-agent-formkey", method = RequestMethod.GET)
    public ResponseEntity getFormkeyForAgentRegistration(@PathVariable String username){

        List<Task> tasks = findTaskByAssignee(username);
        String taskId = tasks.get(tasks.size()-1).getId();
        String formKey = formService.getTaskFormData(taskId).getFormKey();
        System.out.println("taskId = " + taskId + " "+"FORM KEY: " + formKey);
        return new ResponseEntity<>(formKey,HttpStatus.OK);
    }

    @RequestMapping(value = "/get-active-tasks", method = RequestMethod.GET)
    public String getActiveTasks(){
        System.out.println("get active tasks");
        findActiveTaskByProces("registrationProcess");
        return "HELLO";
    }

    @RequestMapping(value = "/get-assignee-tasks", method = RequestMethod.POST)
    public String getAssigneeTasks(@RequestBody String username){
        System.out.println("get assignee tasks");
        findTaskByAssignee(username);
        return "HELLO";
    }

    private void findActiveTaskByProces(String procId){
        try{
            List<Task> tasks = taskService.createTaskQuery().processInstanceId(procId).list();
            System.out.println("tasks.size = " + tasks.size());
            for(Task t : tasks){
                System.out.println("TASK: " + t.toString());
            }

        }catch (Exception e){

        }
    }

    private Task getLastTaskByAssignee(String username){
        List<Task> tasks = findTaskByAssignee(username);
        return tasks.get(tasks.size()-1);
    }

    private List<Task> findTaskByAssignee(String username){
        try{
            List<Task> tasks = taskService.createTaskQuery().taskAssignee(username).list();
            System.out.println("ASSIGNEE tasks.size = " + tasks.size());
            for(Task t : tasks){
                System.out.println("TASK: " + t.toString());
            }
            return tasks;

        }catch (Exception e){
            return null;
        }
    }


}
