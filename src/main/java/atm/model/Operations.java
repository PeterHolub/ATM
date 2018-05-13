package atm.model;

import java.util.Objects;

public class Operations{

    private int operationCode;
    private long cardId;
    private  String operationType;
    private String dateTime;
    private  double balance;
    private double amountWithdrawn;

    public Operations() {
    }

    public Operations(long cardId, String operationType, String dateTime, double balance) {
        this.cardId = cardId;
        this.operationType = operationType;
        this.dateTime = dateTime;
        this.balance = balance;
    }

    public Operations(long cardId, String operationType, String dateTime, double balance, double amountWithdrawn) {
        this.cardId = cardId;
        this.operationType = operationType;
        this.dateTime = dateTime;
        this.balance = balance;
        this.amountWithdrawn = amountWithdrawn;
    }

    public Operations(int operationCode, long cardId, String operationType, String dateTime, double balance, double amountWithdrawn) {
        this.operationCode = operationCode;
        this.cardId = cardId;
        this.operationType = operationType;
        this.dateTime = dateTime;
        this.balance = balance;
        this.amountWithdrawn = amountWithdrawn;
    }

    public int getOperationCode() {
        return operationCode;
    }

    public void setOperationCode(int operationCode) {
        this.operationCode = operationCode;
    }

    public long getCardId() {
        return cardId;
    }

    public void setCardId(long cardId) {
        this.cardId = cardId;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getAmountWithdrawn() {
        return amountWithdrawn;
    }

    public void setAmountWithdraw(double amountWithdrawn) {
        this.amountWithdrawn = amountWithdrawn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operations that = (Operations) o;
        return operationCode == that.operationCode &&
                cardId == that.cardId &&
                Double.compare(that.balance, balance) == 0 &&
                Double.compare(that.amountWithdrawn, amountWithdrawn) == 0 &&
                Objects.equals(operationType, that.operationType) &&
                Objects.equals(dateTime, that.dateTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(operationCode, cardId, operationType, dateTime, balance, amountWithdrawn);
    }

    @Override
    public String toString() {
        return "Operations{" +
                "operationCode=" + operationCode +
                ", cardId=" + cardId +
                ", operationType='" + operationType + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", balance=" + balance +
                ", amountWithdrawn=" + amountWithdrawn +
                '}';
    }
}