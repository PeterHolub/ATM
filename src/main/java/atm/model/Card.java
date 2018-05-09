package atm.model;

import java.io.Serializable;
import java.util.Objects;

public class Card implements Serializable {

    private long cardNumber;
    private int pin;
    private double balance;
    private String status;

    public Card() {
    }

    public Card(long cardNumber, int pin, double balance, String status) {
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.balance = balance;
        this.status = status;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return cardNumber == card.cardNumber &&
                pin == card.pin &&
                balance == card.balance &&
                Objects.equals(status, card.status);
    }

    @Override
    public int hashCode() {

        return Objects.hash(cardNumber, pin, balance, status);
    }


    @Override
    public String toString() {
        return "Card{" +
                "cardNumber=" + cardNumber +
                ", pin=" + pin +
                ", balance=" + balance +
                ", status='" + status + '\'' +
                '}';
    }
}
