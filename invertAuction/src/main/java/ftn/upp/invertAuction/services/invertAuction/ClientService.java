package ftn.upp.invertAuction.services.invertAuction;

import org.springframework.stereotype.Component;

@Component
public class ClientService {

    public void notifyNoCandidates(){
        System.out.println("No candidates notification...");
    }

    public void notifyNotEnoughCandidates(){
        System.out.println("Not enough candidates notification...");
    }
}
