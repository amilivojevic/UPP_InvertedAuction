package ftn.upp.invertAuction.repositories;

import ftn.upp.invertAuction.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Long> {

    public Request findByProcInstId(String procInstId);
}
