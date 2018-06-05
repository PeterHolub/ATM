package com.peterholub.atm.domains;
import com.peterholub.atm.enums.OperationType;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;


@Entity
public class Operations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long operationCode;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cardId")
    private Card card;

    @Enumerated(value = EnumType.STRING)
    private OperationType operationType;

    private LocalDateTime dateTime;
    private Double balance;

    private Double amountWithdrawn;

    public Operations() {
    }

    public Operations(Card card, OperationType operationType, LocalDateTime dateTime, Double balance, Double amountWithdrawn) {
        this.card = card;
        this.operationType = operationType;
        this.dateTime = dateTime;
        this.balance = balance;
        this.amountWithdrawn = amountWithdrawn;
    }

    public Long getOperationCode() {
        return operationCode;
    }

    public void setOperationCode(Long operationCode) {
        this.operationCode = operationCode;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getAmountWithdrawn() {
        return amountWithdrawn;
    }

    public void setAmountWithdrawn(Double amountWithdrawn) {
        this.amountWithdrawn = amountWithdrawn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operations that = (Operations) o;
        return Objects.equals(operationCode, that.operationCode) &&
                Objects.equals(card, that.card) &&
                operationType == that.operationType &&
                Objects.equals(dateTime, that.dateTime) &&
                Objects.equals(balance, that.balance) &&
                Objects.equals(amountWithdrawn, that.amountWithdrawn);
    }

    @Override
    public int hashCode() {

        return Objects.hash(operationCode, card, operationType, dateTime, balance, amountWithdrawn);
    }

    @Override
    public String toString() {
        return "Operations{" +
                "operationCode=" + operationCode +
                ", card=" + card +
                ", operationType=" + operationType +
                ", dateTime=" + dateTime +
                ", balance=" + balance +
                ", amountWithdrawn=" + amountWithdrawn +
                '}';
    }
}
