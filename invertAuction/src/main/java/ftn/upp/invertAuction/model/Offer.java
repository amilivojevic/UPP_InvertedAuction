package ftn.upp.invertAuction.model;

import com.sun.istack.internal.Nullable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class Offer {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private boolean cancelled;

    @Column(nullable = true)
    private double offerPrice;

    @Column(nullable = true)
    private double offerDeadline;

    public Offer(boolean cancelled, double offerPrice, double offerDeadline) {
        this.cancelled = cancelled;
        this.offerPrice = offerPrice;
        this.offerDeadline = offerDeadline;
    }

    public Offer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public double getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(double offerPrice) {
        this.offerPrice = offerPrice;
    }

    public double getOfferDeadline() {
        return offerDeadline;
    }

    public void setOfferDeadline(double offerDeadline) {
        this.offerDeadline = offerDeadline;
    }
}
