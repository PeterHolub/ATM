package atm.model;

import java.io.Serializable;
import java.util.Objects;

public class Operations implements Serializable{

    private int operationCode;
    private long cardId;
    private double amountWithdraw;
    private String dateTime;

    public Operations(long cardId, String dateTime) {
        this.cardId = cardId;
        this.dateTime = dateTime;
    }

    public Operations(long cardId, double amountWithdraw, String dateTime) {
        this.cardId = cardId;
        this.amountWithdraw = amountWithdraw;
        this.dateTime = dateTime;
    }

    public Operations(int operationCode, long cardId, double amountWithdraw, String dateTime) {
        this.operationCode = operationCode;
        this.cardId = cardId;
        this.amountWithdraw = amountWithdraw;
        this.dateTime = dateTime;
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

    public double getAmountWithdraw() {
        return amountWithdraw;
    }

    public void setAmountWithdraw(double amountWithdraw) {
        this.amountWithdraw = amountWithdraw;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operations that = (Operations) o;
        return operationCode == that.operationCode &&
                cardId == that.cardId &&
                Double.compare(that.amountWithdraw, amountWithdraw) == 0 &&
                Objects.equals(dateTime, that.dateTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(operationCode, cardId, amountWithdraw, dateTime);
    }

    @Override
    public String toString() {
        return "Operations{" +
                "operationCode=" + operationCode +
                ", cardId=" + cardId +
                ", amountWithdraw=" + amountWithdraw +
                ", dateTime='" + dateTime + '\'' +
                '}';
    }
}
