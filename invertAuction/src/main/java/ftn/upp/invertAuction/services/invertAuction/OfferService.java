package ftn.upp.invertAuction.services.invertAuction;

import org.activiti.engine.delegate.DelegateExecution;

public class OfferService {

    public void getSubmittedOffers(DelegateExecution execution){
        System.out.println("Get submitted offers...");
    }

    public void analizeSubmittedOffers(DelegateExecution execution){
        System.out.println("\t\t\tAnalize submitted offers...");
    }

    public void rangOffers(DelegateExecution execution){
        System.out.println("\t\t\tRang offers...");
    }
}
