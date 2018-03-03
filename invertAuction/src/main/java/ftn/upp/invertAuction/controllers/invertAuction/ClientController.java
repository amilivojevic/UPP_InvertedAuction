package ftn.upp.invertAuction.controllers.invertAuction;

import org.activiti.engine.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    private FormService formService;

    @RequestMapping(value = "{taskId}/not-enough-candidates-confirmation", method = RequestMethod.POST)
    public ResponseEntity notEnoughCandidatesConfirmation(@PathVariable String taskId, @RequestBody Map<String,String> params){
        System.out.println("\t\t\tNot enough candidates confirmation (user task)");

        formService.submitTaskFormData(taskId,params);
        return new ResponseEntity(HttpStatus.OK);
    }


}
