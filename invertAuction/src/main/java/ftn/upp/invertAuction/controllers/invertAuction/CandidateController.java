package ftn.upp.invertAuction.controllers.invertAuction;

import org.activiti.engine.FormService;
import org.activiti.engine.RuntimeService;
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

    @Autowired
    RuntimeService runtimeService;

    @RequestMapping("/{taskId}/candidate-offer-confirmation")
    public ResponseEntity offerConfirmation(@PathVariable String taskId, @RequestBody Map<String,String> params){
        System.out.println("\t\t\tOffer confirmation (user task" +taskId+
                ")");

        formService.submitTaskFormData(taskId,params);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping("/{taskId}/candidate-make-an-offer")
    public ResponseEntity candidateMakeAnOffer(@PathVariable String taskId, @RequestBody Map<String,String> params){
        System.out.println("\t\t\tCandidate make an offer (user task" +taskId+
                ")");

        formService.submitTaskFormData(taskId,params);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping("/{taskId}/view-rank-and-update")
    public ResponseEntity viewRankAndUpdate(@PathVariable String taskId, @RequestBody Map<String,String> params){
        System.out.println("\t\t\tCandidate make an offer (user task" +taskId+
                ")");

        formService.submitTaskFormData(taskId,params);
        return new ResponseEntity(HttpStatus.OK);
    }
}
