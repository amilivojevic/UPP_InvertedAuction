package ftn.upp.invertAuction.services.invertAuction;

import ftn.upp.invertAuction.model.Company;
import ftn.upp.invertAuction.model.Request;
import ftn.upp.invertAuction.model.User;
import ftn.upp.invertAuction.services.CompanyService;
import ftn.upp.invertAuction.services.EmailService;
import ftn.upp.invertAuction.services.RequestService;
import ftn.upp.invertAuction.services.UserService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class CandidateCompaniesService {

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    CompanyService companyService;

    @Autowired
    UserService userService;

    @Autowired
    EmailService emailService;

    @Autowired
    RequestService requestService;

    public void createList(DelegateExecution execution){

        System.out.println("\t\t\tSaving request in DB...");

        String procInstId = execution.getProcessInstanceId();
        try {
            // client
            String clientUsername = runtimeService.getVariable(procInstId,"client", String.class);
            User client = userService.findByUsername(clientUsername);
            // does client have a company?
            Company company = userService.hasCompany(client);

            long jobCatId = runtimeService.getVariable(procInstId,"job_category", Long.class);
            String jobDesc = runtimeService.getVariable(procInstId, "job_description", String.class);
            System.out.println("*desc: " + jobDesc);
            double jobMaxPrice = runtimeService.getVariable(procInstId, "job_max_price", Double.class);
            System.out.println("*jobMaxPrice: " + jobMaxPrice);
            String jobAppDeadlineString = runtimeService.getVariable(procInstId, "job_application_deadline", String.class);
            System.out.println("*jobAppDeadlineString: " + jobAppDeadlineString);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date jobAppDeadline = df.parse(jobAppDeadlineString);

            String jobDeadlineString = runtimeService.getVariable(procInstId, "job_deadline", String.class);
            Date jobDeadline = df.parse(jobDeadlineString);
            System.out.println("jobDeadlineString: " + jobDeadlineString );

            long jobMinCandidates = runtimeService.getVariable(procInstId, "job_min_candidates", Long.class);
            System.out.println("jobMinCandidates: " + jobMinCandidates);
            long jobMaxCandidates = runtimeService.getVariable(procInstId, "job_max_candidates", Long.class);
            System.out.println("jobMaxCandidates: " + jobMaxCandidates);

            requestService.saveRequest(clientUsername, jobCatId, jobDesc, jobMaxPrice, jobAppDeadline,jobMinCandidates, jobMaxCandidates, jobDeadline, procInstId);



            System.out.println("\t\t\tCreating candidate list...");


            // all the companies suitable to job_category
            List<Company> companies = companyService.findAllByJobCategory(jobCatId);


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


        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void notifyCandidates(Company candidate){
        System.out.println("\t\t\tNotification for candidates...");

        //String procInstId = execution.getProcessInstanceId();
        System.out.println("\n\n");
        System.out.println("candidate: " + candidate.toString());
        /*String candidateUsername = runtimeService.getVariable(procInstId,"candidate", String.class);
        System.out.println("CandidateUserane: " + candidateUsername);
        User candidate = userService.findByUsername(candidateUsername);
        System.out.println("Candidate: " + candidate.toString());*/
        String subject = "New job request you may be interested in";
        String text = "Hello, "+candidate.getAgent().getUsername() + "!\n"
                + "We want to suggest you new job offer on Invert Auction system. Take a look at your profile notifications." +
                "Process id: ";
        emailService.sendMail(candidate.getAgent().getEmail(),subject, text);
    }

    public void getSubmittedOffers(DelegateExecution execution){
        System.out.println("Get submitted offers...");
    }

    public void analizeSubmittedOffers(DelegateExecution execution){
        System.out.println("\t\t\tAnalize submitted offers...");
    }

    public void rangOffers(DelegateExecution execution){
        System.out.println("\t\t\tRang offers...");
    }

    public void saveCanceledOffer(DelegateExecution execution){
        System.out.println("\t\t\tSave Canceled Offer...");
    }
}
