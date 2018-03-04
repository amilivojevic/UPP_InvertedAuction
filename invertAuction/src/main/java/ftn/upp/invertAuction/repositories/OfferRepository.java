package ftn.upp.invertAuction.repositories;

import ftn.upp.invertAuction.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<Offer,Long> {
}
