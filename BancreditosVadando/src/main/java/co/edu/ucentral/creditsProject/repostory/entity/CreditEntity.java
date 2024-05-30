package co.edu.ucentral.creditsProject.repostory.entity;

import co.edu.ucentral.creditsProject.config.CreditType;
import co.edu.ucentral.creditsProject.config.Status;
import co.edu.ucentral.creditsProject.dto.Client;
import co.edu.ucentral.creditsProject.dto.Officer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.sql.Date;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CREDIT")
@Builder
public class CreditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CREDITS")
    @SequenceGenerator(name = "SEQ_CREDITS", sequenceName = "SEQ_CREDITS", allocationSize = 1)
    private int id;
    private double interest;
    @Column(name = "total_amount", nullable = false)
    private double totalAmount;
    @Column(name = "current_amount", nullable = false)
    private double currentAmount;
    @Column(name = "months_time", nullable = false)
    private int monthsTime;
    @Column(name = "date_payment")
    private Date datePayment;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    @Column(name = "credit_type", nullable = false)
    private CreditType creditType;

    @Column(name = "officer_id")
    private String officerId;
    @Column(name = "client_id", nullable = false)
    private String clientId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getMonthsTime() {
        return monthsTime;
    }

    public void setMonthsTime(int monthsTime) {
        this.monthsTime = monthsTime;
    }

    public Date getDatePayment() {
        return datePayment;
    }

    public void setDatePayment(Date datePayment) {
        this.datePayment = datePayment;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public CreditType getCreditType() {
        return creditType;
    }

    public void setCreditType(CreditType creditType) {
        this.creditType = creditType;
    }

    public String getOfficerId() {
        return officerId;
    }

    public void setOfficerId(String officerId) {
        this.officerId = officerId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public double getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(double currentAmount) {
        this.currentAmount = currentAmount;
    }
}
