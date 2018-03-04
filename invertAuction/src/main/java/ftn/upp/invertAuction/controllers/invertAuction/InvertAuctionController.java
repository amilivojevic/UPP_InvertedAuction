package ftn.upp.invertAuction.controllers.invertAuction;

import ftn.upp.invertAuction.dto.StartIADTO;
import ftn.upp.invertAuction.model.User;
import ftn.upp.invertAuction.services.UserService;
import org.activiti.engine.FormService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class InvertAuctionController {

    @Autowired
    RepositoryService repositoryService;

    @Autowired
    FormService formService;

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    IdentityService identityService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/start-ia", method = RequestMethod.POST)
    public ResponseEntity startProcess(@RequestBody StartIADTO startIADTO, @RequestHeader("X-Auth-Token") String token){
        System.out.println("\nStarting new process instance (invertAuction)... ");
        User user = userService.findByToken(token);

        Map<String, String> params = new HashMap<>();
        params.put("job_category", startIADTO.getJob_category());
        params.put("job_description", startIADTO.getJob_description());
        params.put("job_max_price", startIADTO.getJob_max_price());
        params.put("job_application_deadline", startIADTO.getJob_application_deadline());
        params.put("job_min_candidates", startIADTO.getJob_min_candidates());
        params.put("job_max_candidates", startIADTO.getJob_max_candidates());
        params.put("job_deadline", startIADTO.getJob_deadline());

        ProcessDefinition procDef = repositoryService.createProcessDefinitionQuery().processDefinitionKey("invertAuction").latestVersion().singleResult();
        identityService.setAuthenticatedUserId(user.getUsername());
        formService.submitStartFormData(procDef.getId(),params);

        //String procInstName = runtimeService.createProcessInstanceQuery().processInstanceName();
        return new ResponseEntity(HttpStatus.OK);
    }
}
