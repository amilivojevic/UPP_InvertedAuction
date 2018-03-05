package ftn.upp.invertAuction.services.invertAuction;

import ftn.upp.invertAuction.model.Company;
import ftn.upp.invertAuction.model.Offer;
import ftn.upp.invertAuction.model.Request;
import ftn.upp.invertAuction.model.User;
import ftn.upp.invertAuction.services.*;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

    @Autowired
    OfferService offerService;

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

                }
            }

            Collections.shuffle(finalCompanies);
            List<Company> candidateList = new ArrayList<>();
            for(int i=0; i<finalCompanies.size(); i++){
                if(i<jobMaxCandidates){
                    System.out.println("\t\t\t CANDIDATE: " + finalCompanies.get(i).toString());
                    candidateList.add(finalCompanies.get(i));
                }
            }

            runtimeService.setVariable(procInstId,"candidateList", candidateList);


        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    public void createNewList(DelegateExecution execution){
        System.out.println("\t\t\tCreate new candidate list...");

        String procInstId = execution.getProcessInstanceId();
        long jobCatId = runtimeService.getVariable(procInstId,"job_category", Long.class);

        long jobMaxCandidates = runtimeService.getVariable(procInstId, "job_max_candidates", Long.class);

        String clientUsername = runtimeService.getVariable(procInstId,"client", String.class);
        User client = userService.findByUsername(clientUsername);
        // does client have a company?
        Company company = userService.hasCompany(client);

        // all the companies suitable to job_category
        List<Company> companies = companyService.findAllByJobCategory(jobCatId);

        List<Company> candidateList = (List<Company>) runtimeService.getVariable(procInstId, "candidateList",List.class);
        //List<Company> candidateListOLD = (List<Company>)runtimeService.getVariable(procInstId, "candidateListOLD",List.class);


        List<Company> newCompanyList = new ArrayList<Company>();
        for(Company c : companies){
            // nemoj uzimati klijentovu firmu
            if(company != null){
                if(company.getId() == c.getId()){
                    continue;
                }
            }

            //nemoj uzimati firme koje su vec obradjivane
            if(companyService.ifCompanyInList(c,candidateList)){
                continue;
            }

            //ako je dovoljno blizu stavi je u listu
            if (companyService.checkIfCompanyCloseEnough(c,client)){
                newCompanyList.add(c);

            }
        }

        Collections.shuffle(newCompanyList);
        List<Company> newCompanyListResult = new ArrayList<>();
        for(int i=0; i<newCompanyList.size(); i++){
            if(i<jobMaxCandidates){
                System.out.println("\t\t\t NEW CANDIDATE: " + newCompanyListResult.get(i).toString());
                candidateList.add(newCompanyListResult.get(i));
            }
        }

        runtimeService.setVariable(procInstId,"candidateListOLD", candidateList);
        runtimeService.setVariable(procInstId,"candidateList", newCompanyList);
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

    public void getSubmittedOffers(DelegateExecution execution, Company candidate){
        System.out.println("\t\t\tGet submitted offers and calculate rank...");

        String procInstId = execution.getProcessInstanceId();
        Request request = requestService.findByProcInstId(procInstId);
        List<Offer> offers = offerService.findOffersByRequest(request.getId());

        //Offer candidatesOffer = offerService.findOfferByOfferorAndRequest(candidate.getId(), request.getId());

        int rank = 1;
        double price = runtimeService.getVariable(procInstId, "offerPrice", Double.class);
        System.out.println("\t\t\tOFFER PRICE: " + price);
        for (Offer o : offers){

            if(price > o.getOfferPrice()){
                rank++;
            }

        }

        runtimeService.setVariable(procInstId,"offerRank", rank);

    }

    public void saveOffer(DelegateExecution execution, Company candidate){
        System.out.println("\t\t\tSave Offer...");
        try {
            String procInstId = execution.getProcessInstanceId();
            Request request = requestService.findByProcInstId(procInstId);
            System.out.println("SAVE OFFER: (req) " + request.toString());

            String offerJobDeadlineString = runtimeService.getVariable(procInstId, "offerJobDeadline", String.class);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

            Date offerJobDeadline = df.parse(offerJobDeadlineString);

            double offerPrice = runtimeService.getVariable(procInstId, "offerPrice", Double.class);

            Offer offer = new Offer();
            offer.setCancelled(false);
            offer.setOfferor(candidate.getAgent());
            offer.setRequest(request);
            offer.setOfferDeadline(offerJobDeadline);
            offer.setOfferPrice(offerPrice);

            offerService.saveOffer(offer);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void saveCanceledOffer(DelegateExecution execution, Company candidate){
        System.out.println("\t\t\tSave Canceled Offer...");

        String procInstId = execution.getProcessInstanceId();
        Request request = requestService.findByProcInstId(procInstId);
        System.out.println("SAVE CANCELED OFFER: (req) " + request.toString());

        Offer offer = new Offer();
        offer.setCancelled(true);
        offer.setOfferor(candidate.getAgent());
        offer.setRequest(request);
        offerService.saveOffer(offer);

        System.out.println("OFFER SAVED: " + offer.toString());
    }

    public void analizeSubmittedOffers(DelegateExecution execution){
        System.out.println("\t\t\tAnalize submitted offers...");

        String procInstId = execution.getProcessInstanceId();
        Request request = requestService.findByProcInstId(procInstId);
        List<Offer> offers = offerService.findOffersByRequest(request.getId());

        runtimeService.setVariable(procInstId,"offerList", offers);
        List<Company> candidateList = (List<Company>) runtimeService.getVariable(procInstId, "candidateList",List.class);
        List<Company> candidateListOLD = (List<Company>)runtimeService.getVariable(procInstId, "candidateListOLD",List.class);
        if(candidateListOLD != null){
            for(Company c : candidateListOLD){
                candidateList.add(c);
            }
        }
    }

    public void rangOffers(DelegateExecution execution){
        System.out.println("\t\t\tRang offers...");
        String procInstId = execution.getProcessInstanceId();
        List<Offer> offerList = (List<Offer>) runtimeService.getVariable(procInstId, "offerLists",List.class);

        Collections.sort(offerList, new Comparator<Offer>(){
            @Override
            public int compare(Offer o1, Offer o2) {
                if(o1.getOfferPrice() == o2.getOfferPrice()){
                    return 0;
                }
                return o1.getOfferPrice() < o2.getOfferPrice() ? -1 : 1;
            }
        });

        runtimeService.setVariable(procInstId,"offerList", offerList);
    }
}
