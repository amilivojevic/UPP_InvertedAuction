package ftn.upp.invertAuction.services;

import ftn.upp.invertAuction.model.Offer;
import ftn.upp.invertAuction.repositories.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OfferService {

    @Autowired
    OfferRepository offerRepository;

    public void saveOffer(Offer offer){
        offerRepository.save(offer);
    }

    public List<Offer> findOffersByRequest(long requestId){
        List<Offer> result = new ArrayList<>();
        for (Offer o : offerRepository.findAll()){
            if(o.getRequest().getId() == requestId){
                result.add(o);
            }
        }

        return result;
    }

    public Offer findOfferByOfferorAndRequest(long offerorId, long requestId){
        for (Offer o : offerRepository.findAll()){
            if(o.getRequest().getId() == requestId && o.getOfferor().getId() == offerorId){
                return o;
            }
        }

        return null;
    }


}
