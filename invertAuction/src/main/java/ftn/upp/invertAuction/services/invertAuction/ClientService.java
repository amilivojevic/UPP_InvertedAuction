package ftn.upp.invertAuction.services.invertAuction;

import ftn.upp.invertAuction.model.User;
import ftn.upp.invertAuction.services.EmailService;
import ftn.upp.invertAuction.services.UserService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientService {

    @Autowired
    EmailService emailService;

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    UserService userService;

    public void notifyNoCandidates(DelegateExecution execution){
        System.out.println("\t\t\tNo candidates notification...");

        String procInstId = execution.getProcessInstanceId();
        String clientUsername = runtimeService.getVariable(procInstId,"client", String.class);
        User client = userService.findByUsername(clientUsername);
        String subject = "No companies for invert auction " + procInstId + " you have created";
        String text = "Hello, "+clientUsername + "!\n"
                + "Sorry, there are no companies for your invert auction. It will be terminated.";
        emailService.sendMail(client.getEmail(),subject, text);
    }

    public void notifyNotEnoughCandidates(DelegateExecution execution){
        System.out.println("\t\t\tNot enough candidates notification...");

        String procInstId = execution.getProcessInstanceId();
        String clientUsername = runtimeService.getVariable(procInstId,"client", String.class);
        User client = userService.findByUsername(clientUsername);
        String subject = "Not enough companies for invert auction " + procInstId + " you have created";
        String text = "Hello, "+clientUsername + "!\n"
                + "Sorry, there are not enough companies for your invert auction. You have received notification in inbox." +
                " Please confirm there if you want to continue with a smaller number of candidates.";
        emailService.sendMail(client.getEmail(),subject, text);
    }

    public void notEnoughOffersNotification(DelegateExecution execution){
        System.out.println("\t\t\tNot enough offers notification...");
    }
}
