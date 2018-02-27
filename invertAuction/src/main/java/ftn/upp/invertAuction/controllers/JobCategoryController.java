package ftn.upp.invertAuction.controllers;

import ftn.upp.invertAuction.model.JobCategory;
import ftn.upp.invertAuction.repositories.JobCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/job-categories")
public class JobCategoryController {

    @Autowired
    JobCategoryRepository jobCategoryRepository;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity getAll(){
        List<JobCategory> allCategories = jobCategoryRepository.findAll();
        return new ResponseEntity<>(allCategories, HttpStatus.OK);
    }
}
