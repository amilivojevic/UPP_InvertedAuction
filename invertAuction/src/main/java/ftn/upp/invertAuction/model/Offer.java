package ftn.upp.invertAuction.model;

import com.sun.istack.internal.Nullable;

import javax.persistence.*;
import java.util.Date;

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
    private Date offerDeadline;

    @ManyToOne
    private User offeror;

    @ManyToOne
    private Request request;

    public Offer(boolean cancelled, double offerPrice, Date offerDeadline, User offeror, Request request) {
        this.cancelled = cancelled;
        this.offerPrice = offerPrice;
        this.offerDeadline = offerDeadline;
        this.offeror = offeror;
        this.request = request;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", cancelled=" + cancelled +
                ", offerPrice=" + offerPrice +
                ", offerDeadline=" + offerDeadline +
                ", offeror=" + offeror.getUsername() +
                ", request=" + request.getId() +
                '}';
    }

    public Offer() {
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public User getOfferor() {
        return offeror;
    }

    public void setOfferor(User offeror) {
        this.offeror = offeror;
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

    public Date getOfferDeadline() {
        return offerDeadline;
    }

    public void setOfferDeadline(Date offerDeadline) {
        this.offerDeadline = offerDeadline;
    }
}
