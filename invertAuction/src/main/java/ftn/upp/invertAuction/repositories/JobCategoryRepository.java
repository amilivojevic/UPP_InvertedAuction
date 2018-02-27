package ftn.upp.invertAuction.repositories;

import ftn.upp.invertAuction.model.JobCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobCategoryRepository extends JpaRepository<JobCategory,Long> {
}
