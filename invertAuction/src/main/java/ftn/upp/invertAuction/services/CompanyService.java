package ftn.upp.invertAuction.services;

import ftn.upp.invertAuction.model.Company;
import ftn.upp.invertAuction.model.JobCategory;
import ftn.upp.invertAuction.model.User;
import ftn.upp.invertAuction.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;

    public List<Company> findAll(){
        return companyRepository.findAll();
    }

    public List<Company> findAllByJobCategory(long jobCategoryId){

        List<Company> companies = findAll();
        List<Company> companiesByJob = new ArrayList<>();
        for (Company c : companies){
            for (JobCategory j : c.getJobCategories()){
                if (j.getId() == jobCategoryId){
                    companiesByJob.add(c);
                    break;
                }
            }
        }

        return companiesByJob;
    }

    public Boolean checkIfCompanyCloseEnough(Company c, User client){

        //IMPLEMENTIRATI!!!
        return true;
    }

    public boolean ifCompanyInList(Company company, List<Company> listOfCompany){

        for(Company c : listOfCompany){
            if(company.getId() == c.getId()){
                return true;
            }
        }
        return false;

    }

}
