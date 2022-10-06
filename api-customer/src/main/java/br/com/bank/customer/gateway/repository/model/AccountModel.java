package br.com.bank.customer.gateway.repository.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cus_accounts")
public class AccountModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int cusPersonId;
    private int number;
    private int digit;
    private String agency;
    private String status;
    private LocalDateTime openedAt;
    private LocalDateTime closedAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCusPersonId() {
        return cusPersonId;
    }

    public void setCusPersonId(int cusPersonId) {
        this.cusPersonId = cusPersonId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getDigit() {
        return digit;
    }

    public void setDigit(int digit) {
        this.digit = digit;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getOpenedAt() {
        return openedAt;
    }

    public void setOpenedAt(LocalDateTime openedAt) {
        this.openedAt = openedAt;
    }

    public LocalDateTime getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(LocalDateTime closedAt) {
        this.closedAt = closedAt;
    }
}
