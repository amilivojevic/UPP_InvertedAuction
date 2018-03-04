package ftn.upp.invertAuction.services;

import ftn.upp.invertAuction.model.JobCategory;
import ftn.upp.invertAuction.model.Offer;
import ftn.upp.invertAuction.model.Request;
import ftn.upp.invertAuction.model.User;
import ftn.upp.invertAuction.repositories.JobCategoryRepository;
import ftn.upp.invertAuction.repositories.OfferRepository;
import ftn.upp.invertAuction.repositories.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RequestService {

    @Autowired
    UserService userService;

    @Autowired
    JobCategoryRepository jobCategoryRepository;

    @Autowired
    RequestRepository requestRepository;

    public void saveRequest(String clientUsername, long jobCatId, String jobDescription, double jobMaxPrice, Date jobApplicationDeadline, long jobMinCandidates,long jobMaxCandidates,Date jobDeadline, String procInstId ){
        User client = userService.findByUsername(clientUsername);
        JobCategory jobCategory = jobCategoryRepository.findOne(jobCatId);

        Request r = new Request(client,
                jobCategory,
                jobMaxPrice,
                jobDescription,
                jobApplicationDeadline,
                jobDeadline,
                Math.toIntExact(jobMinCandidates),
                Math.toIntExact(jobMaxCandidates),
                procInstId
        );

        requestRepository.save(r);
        System.out.println("Request saved: " + r.toString());
    }
}
