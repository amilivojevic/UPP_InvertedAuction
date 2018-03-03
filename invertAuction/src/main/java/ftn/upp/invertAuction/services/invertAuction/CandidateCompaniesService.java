package ftn.upp.invertAuction.services.invertAuction;

import ftn.upp.invertAuction.model.Company;
import ftn.upp.invertAuction.model.User;
import ftn.upp.invertAuction.services.CompanyService;
import ftn.upp.invertAuction.services.UserService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CandidateCompaniesService {

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    CompanyService companyService;

    @Autowired
    UserService userService;

    public void createList(DelegateExecution execution){

        System.out.println("\t\t\tCreating candidate list...");
        String procInstId = execution.getProcessInstanceId();

        //runtimeService.setVariable(procInstId,"client", "agent1");

        String clientUsername = runtimeService.getVariable(procInstId,"client", String.class);
        User client = userService.findByUsername(clientUsername);
        // does client have a company?
        Company company = userService.hasCompany(client);

        // all the companies suitable to job_category
        long jobId = runtimeService.getVariable(procInstId,"job_category", Long.class);
        List<Company> companies = companyService.findAllByJobCategory(jobId);


        List<Company> finalCompanies = new ArrayList<Company>();
        for(Company c : companies){
            if(company != null){
                if(company.getId() == c.getId()){
                    // this is the client's company
                    // take another company
                    continue;
                }
            }
            if (companyService.checkIfCompanyCloseEnough(c,client)){
                finalCompanies.add(c);
                System.out.println("\t\t\t CANDIDATE: " + c.toString());
            }
        }

        runtimeService.setVariable(procInstId,"candidateList", finalCompanies);
    }

    public void notifyCandidates(){
        System.out.println("\t\t\tNotification for candidates...");
    }
}
