package com.peterholub.atm.domains;

import com.peterholub.atm.enums.CardStatus;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long cardNumber;
    private Integer pin;
    private Double balance;

    @Enumerated(value = EnumType.STRING)
    private CardStatus cardStatus;

    public Card() {
    }

    public Card(Long cardNumber, Integer pin, Double balance, CardStatus cardStatus) {
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.balance = balance;
        this.cardStatus = cardStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Integer getPin() {
        return pin;
    }

    public void setPin(Integer pin) {
        this.pin = pin;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public CardStatus getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(CardStatus cardStatus) {
        this.cardStatus = cardStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(id, card.id) &&
                Objects.equals(cardNumber, card.cardNumber) &&
                Objects.equals(pin, card.pin) &&
                Objects.equals(balance, card.balance) &&
                cardStatus == card.cardStatus;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, cardNumber, pin, balance, cardStatus);
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", cardNumber=" + cardNumber +
                ", pin=" + pin +
                ", balance=" + balance +
                ", cardStatus=" + cardStatus +
                '}';
    }
}
