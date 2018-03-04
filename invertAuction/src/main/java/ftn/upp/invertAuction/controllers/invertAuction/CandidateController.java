package ftn.upp.invertAuction.controllers.invertAuction;

import org.activiti.engine.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/candidate")
public class CandidateController {

    @Autowired
    FormService formService;

    @RequestMapping("/{taskId}/candidate-offer-confirmation")
    public ResponseEntity offerConfirmation(@PathVariable String taskId, @RequestBody Map<String,String> params){
        System.out.println("\t\t\tOffer confirmation (user task)");

        formService.submitTaskFormData(taskId,params);
        return new ResponseEntity(HttpStatus.OK);
    }
}
